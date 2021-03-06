package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
	private ArrayList<TicketBean> cart;
	private String test = "This is a test string.";
	
	public  List<TicketBean> getCart(){
		return cart;
	}
	public void setCart(ArrayList<TicketBean> cart) {
		this.cart = cart;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public void addTicket(TicketBean ticket) {
		ArrayList<TicketBean> tix = new ArrayList<TicketBean>();
		if(cart != null) {
			tix = this.cart;
		}
		tix.add(ticket);
		this.setCart(tix);
	}
//	test function to load all tickets for user 1 into cart
	public void loadTicketsForCust(Connection conn) throws SQLException {
//		PreparedStatement stmt = conn.prepareStatement("SELECT showing.id, showing.date, showing.time, movie.title, movie.rated, movie.release_date, movie.image_link, screen.name, screen.max_rows, screen.max_cols FROM showing JOIN movie ON showing.movie_id = movie.id JOIN screen ON showing.screen_id = screen.id");
//		ResultSet results = stmt.executeQuery();
//		
//		if(results.next()) {
//			date = results.getDate("showing.date");
//			if(date == null) {
//				date = getCurrentDay();
//			}
//			time = results.getTime("showing.time");
//			MovieBean movieBean = new MovieBean();
//			movieBean.setTitle(results.getString("movie.title"));
//			movieBean.setRating(MovieBean.Rating.fromIndex(results.getInt("movie.rated") - 1));
//			movieBean.setReleaseDate(results.getDate("movie.release_date"));
//			movieBean.setImageLink(results.getString("movie.image_link"));
//			movie = movieBean;
//			screenName = results.getString("screen.name");
//			screenRows = results.getInt("screen.max_rows");
//			screenCols = results.getInt("screen.max_cols");
//			return true;
//		} else {
//			return false;
//		}
	}
	
}
