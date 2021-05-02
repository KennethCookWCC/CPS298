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

// A ticketBean holds one seat in one showing of a movie for a customer
public class TicketBean implements Serializable {
	private int ticketId;	// SQL - auto incr
	private int customer_id; //SQL
	private int showing_id;	// SQL
	private int purchase_id;// SQL
	private int price;		// SQL
	private int seatId;		// SQL
	
	private String title;	// added for UI - Movie title
	private String row;		// added for UI - seat Row 
	private int number;		// is this column number? yes
	private java.sql.Date date;	// Showing Date?
	private Time time;		// ShowingTime?
	private boolean purchased = false ;	// UI did it insert?
	

	// EDIT KC - needed to write seatId too
	public boolean insertIntoDatabase(Connection conn) throws SQLException {
		String sql = "INSERT INTO ticket (customer_id, showing_id, seat_id, price, purchase_id) " + 
				"VALUES(?, ?, ?, ?, ?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer_id);
		stmt.setInt(2, showing_id);
		stmt.setInt(3, seatId);
		stmt.setInt(4, price);
		stmt.setInt(5, purchase_id );
		
		System.out.println("TicketBean.insertIntoDatabase:"
				+ "cust:" + customer_id
				+ " showid:" + showing_id
				+ " seatid:" + seatId
				+ " price:" + price
				+ " purchase:" + purchase_id
				);
		
		int retv = stmt.executeUpdate(); 
		
		// if one row affected, it worked -- return true
		if( retv == 1 ) {
			purchased = true;
		} else {
			purchased = false;
		}
		
		System.out.println("TicketBean.insertIntoDatabase:retv:" + retv + " purch:" + purchased );
		
		return( purchased );
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
	
	public int getTicketId() {
		return ticketId;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
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

	@Override
	public String toString() {
		return "TicketBean [ticketId=" + ticketId + ", customer_id=" + customer_id + ", showing_id=" + showing_id
				+ ", price=" + price + ", seatId=" + seatId + ", title=" + title + ", row=" + row + ", number=" + number
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
}
