package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SoldTicketBean implements Serializable {
	private Date date;
	private String movieTitle;
	private String time;
	private int screen;
	private String customer;
	private int ticketId;
	private String seat;
	private int price;
	
	public static List<SoldTicketBean> getSoldTickets(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "";
		sql += "SELECT Date, MovieTitle, ";
		sql += "Time, ";
		sql += "Screen, Customer, TkID, Seat, Price ";
		sql += "FROM SoldTickets";
		
		ResultSet results = stmt.executeQuery( sql );
		List<SoldTicketBean> beans = new ArrayList<>();
		while(results.next()) {
			// create a bean for each ticket result and add them to the list
			SoldTicketBean bean = new SoldTicketBean();
			bean.date = results.getDate("Date");
			bean.movieTitle = results.getString("MovieTitle");
			bean.time = results.getString("Time");
			bean.screen = results.getInt("Screen");
			bean.customer = results.getString("Customer");
			bean.ticketId = results.getInt("TkID");
			bean.seat = results.getString("Seat");
			bean.price = results.getInt("Price");
			beans.add(bean);
		}
		return beans;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getScreen() {
		return screen;
	}
	public void setScreen(int screen) {
		this.screen = screen;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getStringPrice() {
		String retv = "";
		int cents = price % 100;
		int dollars = price / 100;
		retv += dollars + ".";
		if( cents < 10 ) {
			retv += "0";
		}
		retv += cents;
		return retv ;
				
	}
}
