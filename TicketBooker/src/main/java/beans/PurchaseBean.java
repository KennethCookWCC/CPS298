package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class PurchaseBean implements Serializable {
	private int id;
	private int customerId;
	private Date date;
	private Time time;
	private int total;
	private String approval;
	
	/**
	 * Loads from the database. Returns false if no ID match is found.
	 */
	public boolean loadFromDatabase(Connection conn, int id) throws SQLException {
		this.id = id;
		PreparedStatement stmt = conn.prepareStatement("SELECT customer_id, date, time, total, approval FROM purchase WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if(results.next()) {
			customerId = results.getInt("customer_id");
			date = results.getDate("date");
			time = results.getTime("time");
			total = results.getInt("total");
			approval = results.getString("approval");
			return true;
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
