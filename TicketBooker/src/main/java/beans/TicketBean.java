package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketBean implements Serializable {
	private int ticketId;
	private int customer_id;
	private int showing_id;
	private int price;
	
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
	
}
