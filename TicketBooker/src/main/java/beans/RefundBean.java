package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class RefundBean implements Serializable {
	private int id;
	private int customerId;
	private int amount;
	private Date date;
	private Time time;
	private String description;
	
	/**
	 * Loads from the database. Returns false if no ID match is found.
	 */
	public boolean loadFromDatabase(Connection conn, int id) throws SQLException {
		this.id = id;
		PreparedStatement stmt = conn.prepareStatement("SELECT customer_id, amount, date, time, description FROM refund WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if(results.next()) {
			customerId = results.getInt("customer_id");
			amount = results.getInt("amount");
			date = results.getDate("date");
			time = results.getTime("time");
			description = results.getString("description");
			return true;
		}
		return false;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
