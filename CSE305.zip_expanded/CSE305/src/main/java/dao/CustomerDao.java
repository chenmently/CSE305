package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Item;


public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database based on searchKeyword will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		 if(searchKeyword == null)
		 {
			 try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
				 Statement s = con.createStatement();
				 String sql = "select *, LPAD(zipCode, 5,'0') as zip from customer, person where customer.ssn = person.ssn;";
				 ResultSet rs = s.executeQuery(sql);
				 while(rs.next()){
					 Customer customer = new Customer();
					 customer.setCustomerID(rs.getString("customer_id"));
					 customer.setAddress(rs.getString("address"));
					 customer.setLastName(rs.getString("last_name"));
					 customer.setFirstName(rs.getString("first_name"));
					 customer.setCity(rs.getString("city"));
					 customer.setState(rs.getString("state"));
					 customer.setEmail(rs.getString("email"));
					 customer.setZipCode(rs.getString("zip"));
					 customer.setTelephone(rs.getString("phone"));
					 customer.setCreditCard(rs.getString("ccNum"));
					 customer.setRating(rs.getInt("rating"));
					 customers.add(customer);
			      }
			      rs.close();
			 	}
			 	catch(Exception e) {
			 	System.out.println(e);
			 	}
		 }
		 else
		 {
			 try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
				 Statement s = con.createStatement();
				 String sql = "select *, LPAD(zipCode, 5,'0') as zip from customer join person on customer.ssn = person.ssn" + 
				 		" where" + 
				 		"    person.ssn LIKE '%" + searchKeyword + "%'" + 
				 		"    OR ccNum LIKE '%" + searchKeyword + "%'" + 
				 		"    OR phone LIKE '%" + searchKeyword + "%'" + 
				 		"    OR email LIKE '%" + searchKeyword + "%'" + 
				 		"    OR last_name LIKE '%" + searchKeyword + "%'" + 
				 		"    OR first_name LIKE '%" + searchKeyword + "%'" + 
				 		"    OR address LIKE '%" + searchKeyword + "%'" + 
				 		"    OR city LIKE '%" + searchKeyword + "%'" + 
				 		"    OR state LIKE '%" + searchKeyword + "%'" + 
				 		"    OR customer_id LIKE '%" + searchKeyword + "%'";
				 System.out.println(sql);
				 ResultSet rs = s.executeQuery(sql);
				 while(rs.next()){
					 Customer customer = new Customer();
					 customer.setCustomerID(rs.getString("customer_id"));
					 customer.setAddress(rs.getString("address"));
					 customer.setLastName(rs.getString("last_name"));
					 customer.setFirstName(rs.getString("first_name"));
					 customer.setCity(rs.getString("city"));
					 customer.setState(rs.getString("state"));
					 customer.setEmail(rs.getString("email"));
					 customer.setZipCode(rs.getString("zip"));
					 customer.setTelephone(rs.getString("phone"));
					 customer.setCreditCard(rs.getString("ccNum"));
					 customer.setRating(rs.getInt("rating"));
					 customers.add(customer);
			      }
			      rs.close();
			 	}
			 	catch(Exception e) {
			 	System.out.println(e);
			 	}
		 }
		
		 return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select *, SUM(auctions.current_bid) "
					+ "from auctions "
					+ "INNER JOIN sold_items "
						+ "on sold_items.auction_id = sold_items.auction_id "
					+ "INNER JOIN person "
						+ "on sold_items.customer_id = person.ssn "
					+ "GROUP BY person.first_name "
					+ "ORDER BY SUM(auctions.current_bid) desc "
					+ "LIMIT 1");

			while(rs.next()) {
				Customer customer = new Customer();
				customer.setEmail(rs.getString("email"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setCustomerID(rs.getString("customer_id"));
				return customer;
			}
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;

		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		List<Customer> customers = new ArrayList<Customer>();
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();
			 String sql = "select *, LPAD(zipCode, 5,'0') as zip from customer, person where customer.ssn = person.ssn;";
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 Customer customer = new Customer();
				 customer.setCustomerID(rs.getString("ssn"));
				 customer.setAddress(rs.getString("address"));
				 customer.setLastName(rs.getString("last_name"));
				 customer.setFirstName(rs.getString("first_name"));
				 customer.setCity(rs.getString("city"));
				 customer.setState(rs.getString("state"));
				 customer.setEmail(rs.getString("email"));
				 customer.setZipCode(rs.getString("zip"));
				 customers.add(customer);
		      }
		      rs.close();
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 
		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		try {
			Customer customer = new Customer();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			String sql = "select ssn from customer where customer_id = '" +customerID+ "';";
			ResultSet rs = s.executeQuery(sql);
			String ssn ="";
			while(rs.next()){
				 ssn = rs.getString("ssn");
			}
		    sql = "select *, LPAD(zipCode, 5,'0') as zip from customer, person where customer.ssn = person.ssn and person.ssn = '" + ssn + "';";
			rs = s.executeQuery(sql);
			while(rs.next()){

				customer.setCustomerID(rs.getString("customer_id"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("last_name"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getString("zip"));
				customer.setCreditCard(rs.getString("ccNum"));
				customer.setTelephone(rs.getString("phone"));
				customer.setSSN(rs.getString("ssn"));
				 
			}
			rs.close();
			return customer;
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
			 return null;
		 }

	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First delete from customer table
			String sql = "select ssn from customer where customer_id = '" +customerID+ "';";
			ResultSet rs = s.executeQuery(sql);
			String ssn = "";
			while(rs.next()){
				 ssn = rs.getString("ssn");
			}
		    rs.close();
		    sql = "delete from customer where ssn  = '" + ssn + "';";	
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				sql = "delete from person where ssn  = '" + ssn + "';";	
				updated_rows = s.executeUpdate(sql);
				if(updated_rows == 1) return "success";
				else return "failure";
			}
			else return "failure";
			
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
			 return "failure";
		 }
		/*Sample data begins*/
		
		/*Sample data ends*/
		
	}


	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		
		// return their string id NOT SSN
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			String sql = "select ssn from person where email = '" + username + "'";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				return rs.getString("ssn");
			}
			
		 }
		 catch(Exception e) {
			 System.out.println(e);
			 return "failure";
		 }
		return "";
	}


	public List<Customer> getSellers() {
		
		/*
		 * This method fetches the all seller details and returns it
		 * The students code to fetch data from the database will be written here
		 * The seller (which is a customer) record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// String dbPass = System.getenv("DB_PASSWORD");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select *, LPAD(zipCode, 5,'0') as zip " 
					+ "FROM customer "
					+ "INNER JOIN sold_items "
						+ "on sold_items.customer_id = customer.customer_id "
					+ "INNER JOIN person "
						+ "on person.ssn = customer.ssn "
					+ "GROUP BY ssn");
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("customer_id"));
				customer.setAddress(rs.getString("address"));
				customer.setLastName(rs.getString("last_name"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setEmail(rs.getString("email"));
				customer.setZipCode(rs.getString("zip"));
				customers.add(customer);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return customers;

	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		String SSN = customer.getSSN();
		String customerID = customer.getCustomerID();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String Address = customer.getAddress();
		String City = customer.getCity();
		String State = customer.getState();
		String zipCode = customer.getZipCode();
		String telephone = customer.getTelephone();
		String email = customer.getEmail();
		String creditCard = customer.getCreditCard();
		int rating = customer.getRating();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First insert into person table
			String sql = "insert into person values ('"+ SSN +"','" + lastName + "','" + firstName +"','" + Address +"','" + City +"','" + State +"','" + zipCode +"','" + telephone +"','" + email +"');";
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				//Then insert into customer table
				sql = "insert into customer values ('"+ rating + "','" + creditCard + "','" + SSN + "','" + customerID + "');";
				updated_rows = s.executeUpdate(sql);
				if (updated_rows == 1) return "success";
				else return "failure";
			}
			else return "failure";
			
		 }
		 catch(Exception e) {
			 System.out.println(e);
			 return "failure";
		 }
		
		
		
		

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
//		String SSN = customer.getSSN();
		String customerID = customer.getCustomerID();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String Address = customer.getAddress();
		String City = customer.getCity();
		String State = customer.getState();
		String zipCode = customer.getZipCode();
		String telephone = customer.getTelephone();
		String email = customer.getEmail();
		String creditCard = customer.getCreditCard();
		int rating = customer.getRating();
		
		
		// NOTE , BECAUSE OF THE JSP PAGE, customerID ACTUALLY HOLDS A SOCIAL SECURITY # WHILE SSN IS NULL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First insert into person table
			String sql = "update person set state = '" + State + "', last_name = '" + lastName + "', first_name = '" + firstName + "', address = '" + Address + "', zipCode = '" + zipCode + "', city = '" + City + "', phone = '" + telephone  + "', email = '" + email + "' where ssn = '" + customerID + "';";
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				sql = "update customer set ccNum = '" + creditCard + "', rating = '" + rating + "' where ssn = '" + customerID + "';" ;
				updated_rows = s.executeUpdate(sql);
				if (updated_rows == 1) return "success";
				else return "failure";
			}
			else return "failure";
			
		 }
		 catch(Exception e) {
			 System.out.println(e);
			 return "failure";
		 }
	

	}

}
