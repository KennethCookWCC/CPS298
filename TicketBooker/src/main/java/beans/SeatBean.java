package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatBean implements Serializable {
	private String row;	// SQL
	private int number;	// SQL
	private boolean taken; //SQL
	private int seatId;	// UI

	public void setRow(String to) {
		this.row = to;
	}

	public String getRow() {
		return this.row;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean getTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String toString() {
		String numberStr = Integer.toString(number);
		return "Row: " + row + "  Number: " + numberStr + " seatId: " + seatId + "\n";
	}

	// gets all sets for showing id
	public List<SeatBean> getAllSeats(Connection conn, int showingId) throws SQLException {
		List<SeatBean> seatList = new ArrayList<>();
		String sql = "";
		sql += "SELECT seat.id, seat.row, seat.seat_number ";
		sql += "FROM seat ";
		sql += "JOIN screen  ON seat.screen_id = screen.id ";
		sql += "JOIN showing ON screen.id = showing.screen_id ";
		sql += "WHERE showing.id=?";

		// PreparedStatement selectStmt = conn.prepareStatement(" Select seat.id,
		// seat.row, seat.seat_number from seat join screen on seat.screen_id= screen.id
		// join showing on screen.id = showing.screen_id where showing.id=?");
		PreparedStatement selectStmt = conn.prepareStatement(sql);
		selectStmt.setInt(1, showingId);
		ResultSet results = selectStmt.executeQuery();
		while (results.next()) {
			SeatBean sb = new SeatBean();
			sb.setSeatId(results.getInt("seat.id"));
			sb.setRow(results.getString("seat.row"));
			sb.setNumber(results.getInt("seat.seat_number"));
			seatList.add(sb);
		}

		// is this generating a ton of output?
		// System.out.println(seatList.toString());
		return seatList;
	}

	// get one seat
	public Boolean loadOneFromDatabase(Connection conn, int seatid ) throws SQLException {
		String sql = "";
		sql += "SELECT seat.row, seat.seat_number ";
		sql += "FROM seat ";
		sql += "WHERE seat.id=?";

		// PreparedStatement selectStmt = conn.prepareStatement(" Select seat.id,
		// seat.row, seat.seat_number from seat join screen on seat.screen_id= screen.id
		// join showing on screen.id = showing.screen_id where showing.id=?");
		PreparedStatement selectStmt = conn.prepareStatement(sql);
		selectStmt.setInt(1, seatid );
		ResultSet results = selectStmt.executeQuery();
		if (results.next()) {
			
			this.row = results.getString("seat.row");
			this.number = results.getInt("seat.seat_number");
			
			return true;
		
		} else {

			return false;
		}
		
	}

	public ArrayList<Integer> getTakenSeats(Connection conn, int showingId) throws SQLException {
		ArrayList<Integer> taken = new ArrayList<>();
		PreparedStatement selectTakenStmt = conn
				.prepareStatement("select ticket.seat_id from ticket where ticket.showing_id =?");
		selectTakenStmt.setInt(1, showingId);

		ResultSet takenResults = selectTakenStmt.executeQuery();
		while (takenResults.next()) {
			taken.add(takenResults.getInt("ticket.seat_id"));
		}
		return taken;
	}
}
