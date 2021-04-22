package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBean implements Serializable {
	private int id;
	private String name;
	private String login;
	private String passwd;
	
	/**
	 * Loads from the database. Returns false if no ID match is found.
	 */
	public boolean loadFromDatabase(Connection conn, int id) throws SQLException {
		this.id = id;
		PreparedStatement stmt = conn.prepareStatement("SELECT name, login, passwd FROM admin WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if(results.next()) {
			name = results.getString("name");
			login = results.getString("login");
			passwd = results.getString("passwd");
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
