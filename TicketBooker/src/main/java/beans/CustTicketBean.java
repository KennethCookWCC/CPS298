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

public class CustTicketBean implements Serializable {
	private int customerId;
	private int showingId;
	private Date date;
	private Time time;
	private int movieId;
	private String title;
	private String seat;
	private int purchaseId;
	private int price;
	
	public static List<CustTicketBean> loadForCustomer(Connection conn, int customerId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT showing_id, date, time, movie_id, title, seat, purchase_id, price"
				+ "FROM CustTicket WHERE customer_id = ?");
		stmt.setInt(1, customerId);
		ResultSet results = stmt.executeQuery();
		List<CustTicketBean> beans = new ArrayList<>();
		while(results.next()) {
			CustTicketBean bean = new CustTicketBean();
			bean.customerId = customerId;
			bean.showingId = results.getInt("showing_id");
			bean.date = results.getDate("date");
			bean.time = results.getTime("time");
			bean.movieId = results.getInt("movie_id");
			bean.title = results.getString("title");
			bean.seat = results.getString("seat");
			bean.purchaseId = results.getInt("purchase_id");
			bean.price = results.getInt("price");
			beans.add(bean);
		}
		return beans;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getShowingId() {
		return showingId;
	}
	public void setShowingId(int showingId) {
		this.showingId = showingId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
