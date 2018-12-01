package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	
	public List<Item> getSalesReport(Post post) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Query to get sales report for a particular month must be implemented
		 * post, which has details about the month and year for which the sales report is to be generated, is given as method parameter
		 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the expireDate attribute of post object
		 * The month and year can be accessed by getter method, i.e., post.getExpireDate()
		 */

		List<Item> items = new ArrayList<Item>();
				
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Item item = new Item();
//			item.setName("Sample item");
//			item.setSoldPrice(100);
//			items.add(item);
//		}
		/*Sample data ends*/
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
//			 String dbPass = System.getenv("DB_PASSWORD");
			 
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();
			 String[] date = post.getExpireDate().split("-");

			 ResultSet query_results = s.executeQuery("select * from auctions "
			 		+ "inner join item "
			 			+ "on item.item_id = auctions.item_id "
			 		+ "inner join bid "
			 			+ "on bid.auction_id = auctions.auction_id "
			 		+ "where auctions.is_closed = 1 and"
			 			+ " MONTH(bid_time) = " + date[0] + " and YEAR(bid_time) = " + date[1]
			 			+ " and auctions.current_high_bid = bid.bid_price");
			 while(query_results.next()) {
				 Item item = new Item();
				 item.setItemID(query_results.getInt("item_id"));
				 item.setName(query_results.getString("name"));
				 item.setType(query_results.getString("type"));
				 item.setNumCopies(query_results.getInt("num_copies"));
				 item.setDescription(query_results.getString("description"));
				 item.setSoldPrice(query_results.getInt("current_high_bid"));
				 item.setYearManufactured(query_results.getInt("year_manufactured"));
				 items.add(item);
			 }
			 
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }

		return items;
		
	}
}
