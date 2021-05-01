package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  CustomerBean -- 
 */
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName, lastName, middleName;
	private String name; // a nice name for web pages
	private Date birthDate;
	private String zipcode;
	private String login;
	private String passwd;
	private String email;
	
	
	/**
	 * Loads from the database. Returns false if no ID match is found.
	 */
	public boolean loadFromDatabase(Connection conn, int id) throws SQLException {
		this.id = id;
		PreparedStatement stmt = conn.prepareStatement("SELECT first_name, last_name, middle_name, birth_date, zipcode, login, passwd, email " 
				+ "FROM customer WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if(results.next()) {
			firstName = results.getString("first_name");
			lastName = results.getString("last_name");
			middleName = results.getString("middle_name");
			name = "";
			if( firstName != null ) { name += firstName + " "; }
			if( lastName != null ) { name += lastName ; }
			birthDate = results.getDate("birth_date");
			zipcode = results.getString("zipcode");
			login = results.getString("login");
			passwd = results.getString("passwd");
			// passwd = "";
			email = results.getString("email");
			return true;
		}
		return false;
	}
	
	public boolean insertIntoDatabase(Connection conn) throws SQLException {
		String sql = "INSERT INTO customer(first_name, last_name, middle_name, birth_date, zipcode, login, passwd, email)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setString(3, middleName);
		stmt.setDate(4, birthDate);
		stmt.setString(5, zipcode);
		stmt.setString(6, login);
		stmt.setString(7, passwd);
		stmt.setString(8, email);
		return stmt.executeUpdate() == 1; // if one row affected it worked
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
