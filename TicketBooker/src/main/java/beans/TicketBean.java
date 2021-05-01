package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TicketBean implements Serializable {
	private int ticketId;
	private int customer_id;
	private int showing_id;
	private int price;
	private int seatId;
	private String title;
	private String row;
	private int number;
	private java.sql.Date date;
	private Time time;
	

	public boolean insertIntoDatabase(Connection conn) throws SQLException {
		String sql = "INSERT INTO ticket (customer_id, showing_id, price) " + 
				"VALUES(?, ?, ?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer_id);
		stmt.setInt(2, showing_id);
		stmt.setInt(3, price);
		return stmt.executeUpdate() == 1; // if one row affected, it worked -- return true
	}
	
	public int getId() {
		return this.ticketId;
	}
	public void setId(int id) {
		this.ticketId=id;
	}
	public int getCustomerId() {
		return this.customer_id;
	}
	public void setCustomerId(int customerId) {
		this.customer_id= customerId;
	}
	public int getShowing_id() {
		return showing_id;
	}
	public void setShowing_id(int showing_id) {
		this.showing_id = showing_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TicketBean [ticketId=" + ticketId + ", customer_id=" + customer_id + ", showing_id=" + showing_id
				+ ", price=" + price + ", seatId=" + seatId + ", title=" + title + ", row=" + row + ", number=" + number
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
}
