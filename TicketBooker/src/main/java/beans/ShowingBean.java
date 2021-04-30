package beans;

/*
 *  ShowingBean - change showing dates to list of String
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShowingBean implements Serializable {
	private static final DateFormat TIME_FORMAT = DateFormat.getTimeInstance(DateFormat.SHORT);
	private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
	
	private Time time;
	private Date date;
	private List<SeatBean> seats = new ArrayList<>();
	private MovieBean movie;
	private int movieId;
	private int screenId;
	private String screenName;
	private int screenRows, screenCols;
	private int showingId;
	
	
	
	public void setTime(Time time) {
		this.time = time;
	}
	public Time getTime() {
		return time;
	}
	public String getTimeString() {
		return TIME_FORMAT.format(time);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateString() {
		return DATE_FORMAT.format(date);
	}
	
	public List<SeatBean> getSeats() {
		return seats;
	}
	public void setSeats(List<SeatBean> seats) {
		this.seats = seats;
	}
	
	public MovieBean getMovie() {
		return movie;
	}
	public void setMovie(MovieBean movie) {
		this.movie = movie;
	}
	
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public int getScreenRows() {
		return screenRows;
	}
	public void setScreenRows(int screenRows) {
		this.screenRows = screenRows;
	}
	
	public int getScreenCols() {
		return screenCols;
	}
	public void setScreenCols(int screenCols) {
		this.screenCols = screenCols;
	}
	public int getShowingId() {
		return showingId;
	}
	public void setShowingId(int showingId) {
		this.showingId = showingId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String toString() {
		return "showingId: "+showingId + " time: "+ time +" movieId: "+movieId;
	}
	
	public boolean loadOneFromDatabase(Connection conn, int id) throws SQLException {
		String sql = "";
		sql += "SELECT showing.id, showing.date, showing.time, ";
		sql +=   "movie.title, movie.rated, movie.release_date, movie.image_link, ";
		sql +=   "screen.name, screen.max_rows, screen.max_cols ";
		sql += "FROM showing ";
		sql += "JOIN movie ON showing.movie_id = movie.id ";
		sql += "JOIN screen ON showing.screen_id = screen.id ";
		sql += "WHERE showing.id = ?";
		// PreparedStatement stmt = conn.prepareStatement("SELECT showing.id, showing.date, showing.time, movie.title, movie.rated, movie.release_date, movie.image_link, screen.name, screen.max_rows, screen.max_cols FROM showing JOIN movie ON showing.movie_id = movie.id JOIN screen ON showing.screen_id = screen.id WHERE showing.id = ?");
		PreparedStatement stmt = conn.prepareStatement( sql );
		
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		
		if(results.next()) {
			date = results.getDate("showing.date");
			if(date == null) {
				date = getCurrentDay();
			}
			time = results.getTime("showing.time");
			showingId=results.getInt("showing.id");
			
			screenRows=results.getInt("screen.max_rows");
			screenCols=results.getInt("screen.max_cols");
			
			MovieBean movieBean = new MovieBean();
			movieBean.setTitle(results.getString("movie.title"));
			movieBean.setRating(MovieBean.Rating.fromIndex(results.getInt("movie.rated") - 1));
			movieBean.setReleaseDate(results.getDate("movie.release_date"));
			movieBean.setImageLink(results.getString("movie.image_link"));
			movie = movieBean;
			screenName = results.getString("screen.name");
			screenRows = results.getInt("screen.max_rows");
			screenCols = results.getInt("screen.max_cols");
			return true;
		} else {
			return false;
		}
	}
	
	public static List<ShowingBean> loadAllFromDatabase(Connection conn) throws SQLException {
		String sql = "";
		sql += "SELECT showing.id, showing.date, showing.time, ";
		sql +=   "movie.title, movie.rated, movie.release_date, movie.image_link, ";
		sql +=   "screen.name, screen.max_rows, screen.max_cols ";
		sql += "FROM showing ";
		sql += "JOIN movie ON showing.movie_id = movie.id ";
		sql += "JOIN screen ON showing.screen_id = screen.id ";
		// sql += "WHERE showing.id = ?";
		// PreparedStatement stmt = conn.prepareStatement("SELECT showing.id, showing.date, showing.time, movie.title, movie.rated, movie.release_date, movie.image_link, screen.name, screen.max_rows, screen.max_cols FROM showing JOIN movie ON showing.movie_id = movie.id JOIN screen ON showing.screen_id = screen.id");
		PreparedStatement stmt = conn.prepareStatement( sql );
		ResultSet results = stmt.executeQuery();
		List<ShowingBean> beans = new ArrayList<>();
		while(results.next()) {
			ShowingBean bean = new ShowingBean();
			bean.date = results.getDate("showing.date");
			if(bean.date == null) {
				bean.date = getCurrentDay();
			}
			bean.time = results.getTime("showing.time");
			MovieBean movieBean = new MovieBean();
			movieBean.setTitle(results.getString("movie.title"));
			movieBean.setRating(MovieBean.Rating.fromIndex(results.getInt("movie.rated") - 1));
			movieBean.setReleaseDate(results.getDate("movie.release_date"));
			movieBean.setImageLink(results.getString("movie.image_link"));
			bean.movie = movieBean;
			bean.screenName = results.getString("screen.name");
			bean.screenRows = results.getInt("screen.max_rows");
			bean.screenCols = results.getInt("screen.max_cols");
			beans.add(bean);
		}
		return beans;
	}
	
	// change to arraylist of String
	public ArrayList<String> getShowingDates(Connection conn)throws SQLException{
		ArrayList<String> dateArr = new ArrayList<String>();
		PreparedStatement stmt=conn.prepareStatement("SELECT date FROM showing GROUP BY date");
		ResultSet results = stmt.executeQuery();
		while(results.next()) {
			Date date = results.getDate("date");
			System.out.println("date: "+date.toString());
			dateArr.add(date.toString());
		}
		return dateArr;
	}
	private static Date getCurrentDay() {
		return Date.valueOf(LocalDate.now());
	}
	
	public boolean insertIntoDatabase(Connection conn) throws SQLException {
		String sql = "INSERT INTO showing(date, time, movie_id, screen_id) "
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDate(1, date);
		stmt.setTime(2, time);
		stmt.setInt(3, movieId);
		stmt.setInt(4, screenId);
		return stmt.executeUpdate() == 1; // if 1 row affected it worked
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
}
