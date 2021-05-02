package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class PurchaseBean implements Serializable {
	private int id;
	private int customerId;
	private Date date;
	private Time time;
	private int total;
	private int subtotal;	// KC added subtotal
	private int salestax;	// KC added salestax
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
	
	// KC - need recid of created record
	public boolean insertIntoDatabase(Connection conn) throws SQLException {
		String sql = "INSERT INTO purchase(customer_id, date, time, total, approval) " +
				"VALUES(?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, customerId);
		stmt.setDate(2, date);
		stmt.setTime(3, time);
		stmt.setInt(4, total);
		stmt.setString(5, approval);
		int retv =  stmt.executeUpdate();
		int recid = -1;
		ResultSet rs = stmt.getGeneratedKeys();
		if( rs.next() ) {
			recid = rs.getInt(1);
		
			id = recid; 
		}
		
		System.out.println("PurchaseBean:INSERT:retv:" + retv + " recid:"+recid );
		
		// if 1 row is updated it inserted successfully so return true
		return( retv == 1 );
	}
	
	public boolean updateDatabase(Connection conn) throws SQLException {
		String sql = "UPDATE purchase " ;
			sql += "SET customer_id=?, date=?, time=?, total=?, approval=? " +
				"WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		stmt.setDate(2, date);
		stmt.setTime(3, time);
		stmt.setInt(4, total);
		stmt.setString(5, approval);
		stmt.setInt(6, id);
		int retv = stmt.executeUpdate();
	
		System.out.println("PurchaseBean:UPDATE:retv:" + retv + " recid:"+id );
		
		// if 1 row is updated it inserted successfully so return true
		return( retv == 1 ); 
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
	
	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public int getSalestax() {
		return salestax;
	}

	public void setSalestax(int salestax) {
		this.salestax = salestax;
	}

	public String getStringTotal() {
		String retv = "";
		int cents = total % 100;
		int dollars = total / 100;
		retv += dollars + ".";
		if( cents < 10 ) {
			retv += "0";
		}
		retv += cents;
		return retv ;
				
	}
	public String getStringSalesTax() {
		String retv = "";
		int cents = salestax % 100;
		int dollars = salestax / 100;
		retv += dollars + ".";
		if( cents < 10 ) {
			retv += "0";
		}
		retv += cents;
		return retv ;
				
	}
	public String getStringSubtotal() {
		String retv = "";
		int cents = subtotal % 100;
		int dollars = subtotal / 100;
		retv += dollars + ".";
		if( cents < 10 ) {
			retv += "0";
		}
		retv += cents;
		return retv ;
				
	}
}
