package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
	private List<TicketBean> cart;
	private String test = "This is a test string.";
	
	public  List<TicketBean> getCart(){
		return cart;
	}
	public void setCart(List<TicketBean> cart) {
		this.cart = cart;
	}
	public String getTest() {
		return test;
	}
//	test function to load all ticket for user 1 into cart
	public boolean loadTicketsForCust(Connection conn) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT showing.id, showing.date, showing.time, movie.title, movie.rated, movie.release_date, movie.image_link, screen.name, screen.max_rows, screen.max_cols FROM showing JOIN movie ON showing.movie_id = movie.id JOIN screen ON showing.screen_id = screen.id");
		ResultSet results = stmt.executeQuery();
		
		if(results.next()) {
			Date date = results.getDate("showing.date");
			if(date == null) {
				date = getCurrentDay();
			}
			Time time = results.getTime("showing.time");
			MovieBean movieBean = new MovieBean();
			movieBean.setTitle(results.getString("movie.title"));
			movieBean.setRating(MovieBean.Rating.fromIndex(results.getInt("movie.rated") - 1));
			movieBean.setReleaseDate(results.getDate("movie.release_date"));
			movieBean.setImageLink(results.getString("movie.image_link"));
			MovieBean movie = movieBean;
			String screenName = results.getString("screen.name");
			int screenRows = results.getInt("screen.max_rows");
			int screenCols = results.getInt("screen.max_cols");
			return true;
		} else {
			return false;
		}
	}
	private Date getCurrentDay() {
		// TODO Auto-generated method stub
		// Year Month Day
		Date testdaten = new Date(2021, 1, 15);
		return null;
	}
	
}
