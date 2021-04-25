package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

public class UserDAO {
	
	public UserBean checkCustomerLogin( Connection conn, String login, String password) 
			throws SQLException {
		int	uid;
		String firstName ;
		String lastName ;
		String name = "";
		
		
		String sql = "SELECT id, login, first_name, last_name, email "
				+ "FROM customer WHERE login = ? and passwd = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, login);
		statement.setString(2, password);

		ResultSet result = statement.executeQuery();

		UserBean user = null;

		if (result.next()) {
			user = new UserBean();
			
			uid = result.getInt("id");
			user.setId(uid);
			user.setAdmin(false);
			user.setLoginOK(true);
		
			firstName = result.getString("first_name");
			lastName = result.getString("last_name");
			name = "";
			if( firstName != null ) { name += firstName + " "; }
			if( lastName != null ) { name += lastName ; }
			
			user.setName(name.trim());
			user.setEmail(result.getString("email"));
			user.setLogin(result.getString("login"));
			
		}

		return user;
	}

	public UserBean checkAdminLogin( Connection conn, String login, String password) 
			throws SQLException {
		int	uid;
				
		String sql = "SELECT id, login, name, email "
				+ "FROM admin WHERE login = ? and passwd = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, login);
		statement.setString(2, password);

		ResultSet result = statement.executeQuery();

		UserBean user = null;

		if (result.next()) {
			user = new UserBean();
			
			uid = result.getInt("id");
			user.setId(uid);
			user.setAdmin(true);
			user.setLoginOK(true);		
			user.setName(result.getString("name"));
			user.setEmail(result.getString("email"));
			user.setLogin(result.getString("login"));
			
		}

		return user;
	}

}
