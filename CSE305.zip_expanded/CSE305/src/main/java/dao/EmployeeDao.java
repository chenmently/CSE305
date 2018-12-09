package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		String first_name = employee.getFirstName();
		String last_name = employee.getLastName();
		String addr = employee.getAddress();
		String city = employee.getCity();
		String state = employee.getState();
		String zip = employee.getZipCode();
		String email = employee.getEmail();
		String phone = employee.getTelephone();
		
		String employee_id = employee.getEmployeeID();
		String start_date = employee.getStartDate();
		float rate = employee.getHourlyRate();
		String level = employee.getLevel();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First insert into person table
			String sql = "insert into 'person' ('ssn', 'last_name', 'first_name', 'address', 'city', 'state', 'zipCode', 'phone', 'email') values ('"+ employee_id +"','" + last_name + "','" + first_name+"','" + addr +"','" + city +"','" + state +"','" + zip +"','"+ phone +"','" + email +"');";
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				//Then insert into customer table
				sql = "insert into 'employee' ('start_date','hourly_rate','employee_id','level') values ('"+ start_date + "','" + rate + "','" + employee_id + "','" + level + "');";
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

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		String first_name = employee.getFirstName();
		String last_name = employee.getLastName();
		String addr = employee.getAddress();
		String city = employee.getCity();
		String state = employee.getState();
		String zip = employee.getZipCode();
		String email = employee.getEmail();
		String phone = employee.getTelephone();
		
		String employee_id = employee.getEmployeeID();
		String start_date = employee.getStartDate();
		float rate = employee.getHourlyRate();
		String level = employee.getLevel();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First insert into person table
			String sql = "update person set state = '" + state + "', last_name = '" + last_name+ "', first_name = '" + first_name + "', address = '" + addr + "', zipCode = '" + zip+ "', city = '" + city + "', phone = '" + phone + "', email = '" + email + "' where ssn = '" + employee_id+ "';";
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				sql = "update employee set start_date = '" + start_date +"', hourly_rate = '" + rate + "', level = '" + level + "' where employee_id = '" + employee_id+ "';" ;
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

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First delete from customer table
			String sql = "select ssn from employee where employee_id = '" + employeeID + "';";
			ResultSet rs = s.executeQuery(sql);
			String ssn = "";
			while(rs.next()){
				 ssn = rs.getString("ssn");
			}
		    rs.close();
		    sql = "delete from employee where employee_id = '" + ssn + "';";	
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

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select *, LPAD(zipCode, 5,'0') as zip from employee INNER JOIN person on person.ssn = employee.employee_id");

			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setAddress(rs.getString("address"));
				employee.setCity(rs.getString("city"));
				employee.setStartDate(rs.getString("start_date"));
				employee.setState(rs.getString("state"));
				employee.setZipCode(rs.getString("zip"));
				employee.setTelephone(rs.getString("phone"));
				employee.setEmployeeID(rs.getString("employee_id"));
				employee.setHourlyRate(rs.getFloat("hourly_rate"));
				employees.add(employee);
			}
		    rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}

		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select *, LPAD(zipCode, 5,'0') as zip from employee INNER JOIN person on person.ssn = employee.employee_id");

			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setAddress(rs.getString("address"));
				employee.setCity(rs.getString("city"));
				employee.setStartDate(rs.getString("start_date"));
				employee.setState(rs.getString("state"));
				employee.setZipCode(rs.getString("zip"));
				employee.setTelephone(rs.getString("phone"));
				employee.setEmployeeID(rs.getString("employee_id"));
				employee.setHourlyRate(rs.getFloat("hourly_rate"));
				return employee;
			}
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;

	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select *, SUM(auctions.current_bid) "
					+ "from person "
					+ "INNER JOIN auctions "
						+ "on person.ssn = auctions.monitor_id "
					+ "INNER JOIN sold_items "
						+ "on sold_items.auction_id = auctions.auction_id "
					+ "GROUP BY person.first_name "
					+ "ORDER BY SUM(auctions.current_bid) desc "
					+ "LIMIT 1");

			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setEmployeeID(rs.getString("employee_id"));
				return employee;
			}
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
		
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select user_id from user where username = " + username);
			String id = "";
			if(rs.next()) {
				id= rs.getString("user_id");
			}
			rs.close();
			return id;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
