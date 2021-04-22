package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBean implements Serializable {
	private int id;
	private String firstName, lastName, middleName;
	private Date birthDate;
	private String zipcode;
	private String login;
	private String passwd;
	
	/**
	 * Loads from the database. Returns false if no ID match is found.
	 */
	public boolean loadFromDatabase(Connection conn, int id) throws SQLException {
		this.id = id;
		PreparedStatement stmt = conn.prepareStatement("SELECT first_name, last_name, middle_name, birth_date, zipcode, login, passwd FROM customer WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if(results.next()) {
			firstName = results.getString("first_name");
			lastName = results.getString("last_name");
			middleName = results.getString("middle_name");
			birthDate = results.getDate("birth_date");
			zipcode = results.getString("zipcode");
			login = results.getString("login");
			passwd = results.getString("passwd");
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
