package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */

	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or
		 * "customer" if successful login Else, return null The role depends on the type
		 * of the user, which has to be handled in the database username, which is the
		 * email address of the user, is given as method parameter password, which is
		 * the password of the user, is given as method parameter Query to verify the
		 * username and password and fetch the role of the user, must be implemented
		 */

		/* Sample data begins */
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);

		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			String dbPass = System.getenv("DB_PASSWORD");
//			System.out.println(dbPass);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			// query database for a row that matches user and pass
			ResultSet query_results = s.executeQuery("select role from user where username = '" + username+ "' and password = '" + password +"'");

			// if row is returned, set the role and return login object
			if (query_results.next()) {
				login.setRole(query_results.getString("role"));
				return login;
			} else
				return null;

		} catch (Exception e) {
			System.out.println("TEST" +e);
		}

		// login.setRole("customerRepresentative");
		return null;
		/* Sample data ends */

	}

	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented login, which
		 * is the "Login" Class object containing username and password for the new
		 * user, is given as method parameter The username and password from login can
		 * get accessed using getter methods in the "Login" model e.g. getUsername()
		 * method will return the username encapsulated in login object Return "success"
		 * on successful insertion of a new user Return "failure" for an unsuccessful
		 * database operation
		 */
		
		/* Sample data begins */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();

			String new_username = login.getUsername();
			String new_pwd = login.getPassword();
			String new_role = login.getRole() != null ? login.getRole() : "customer";

			// create query to add row to user table
			int updated_rows = s.executeUpdate("insert into user (username, password, role) values ('"+ new_username+"','" + new_pwd + "','" + new_role + "')");
			if (updated_rows == 1) return "success";
			else return "failure";


		} catch (Exception e) {
			System.out.println(e);
		}
		return "failure";
		/* Sample data ends */
	}

}
