package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {
	
	public List<Auction> getAllAuctions() {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions should be implemented
		 */
		
		/*Sample data begins*/
	
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();
			 String sql = "select * from auction";
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 Auction auction = new Auction();
				 auction.setAuctionID(rs.getInt("auction_id"));
				 auction.setBidIncrement(rs.getFloat("bid_increment"));
				 auction.setMinimumBid(rs.getFloat("minimum_bid"));
				 auction.setCopiesSold(rs.getInt("copies_sold"));
				 auction.setItemID(rs.getInt("item_id"));
				 auction.setClosingBid(rs.getInt("current_bid"));
				 auction.setCurrentBid(rs.getInt("current_bid"));
				 auction.setCurrentHighBid(rs.getInt("current_bid"));
				 auction.setReserve(rs.getInt("reserve"));
				 auctions.add(auction);
		      }
		      rs.close();
		 	}
		 	catch(Exception e) {
		 	System.out.println(e);
		 	}
		
		return auctions;

	}

	public List<Auction> getAuctions(String customerID) {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions in which a customer participated should be implemented
		 * customerID is the customer's primary key, given as method parameter
		 */

		if(customerID != null)
		 {
			 try {

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();


			 String sql = "select * from auction where auction_id in " + " select auction_id from bid where customer_id = " + customerID;
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 Auction auction = new Auction();
				 auction.setAuctionID(rs.getInt("auction_id"));
				 auction.setBidIncrement(rs.getFloat("bid_increment"));
				 auction.setMinimumBid(rs.getFloat("minimum_bid"));
				 auction.setCopiesSold(rs.getInt("copies_sold"));
				 auction.setItemID(rs.getInt("item_id"));
				 auction.setClosingBid(rs.getInt("current_bid"));
				 auction.setCurrentBid(rs.getInt("current_bid"));
				 auction.setCurrentHighBid(rs.getInt("current_bid"));
				 auction.setReserve(rs.getInt("reserve"));
				 auctions.add(auction);
		      }
		      rs.close();

			 	}
			 	catch(Exception e) {
			 	System.out.println(e);
			 	}
		 }
		
	
		return auctions;

	}

	public List<Auction> getOpenAuctions(String employeeEmail) {
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the open auctions monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		
		/*Sample data begins*/
		if(employeeEmail != null)
		 {
			 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			 String sql = "select * from auction INNER JOIN person on person.ssn = auction.monitor_id where is_closed = 0 and email like \'%" + employeeEmail + "%\'";
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 Auction auction = new Auction();
				 auction.setAuctionID(rs.getInt("auction_id"));
				 auction.setBidIncrement(rs.getFloat("bid_increment"));
				 auction.setMinimumBid(rs.getFloat("minimum_bid"));
				 auction.setCopiesSold(rs.getInt("copies_sold"));
				 auction.setItemID(rs.getInt("item_id"));
				 auction.setClosingBid(rs.getInt("current_bid"));
				 auction.setCurrentBid(rs.getInt("current_bid"));
				 auction.setCurrentHighBid(rs.getInt("current_bid"));
				 auction.setReserve(rs.getInt("reserve"));
				 auctions.add(auction);
		      }
		      rs.close();

			 	}
			 	catch(Exception e) {
			 	System.out.println(e);
			 	}
		 }

		/*Sample data ends*/
		
		return auctions;

		
		
	}

	public String recordSale(String auctionID) {


		if(auctionID != null)
		 {
			 try {

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			 String insert = "insert into auction (auction_id) values ('"+ auctionID + "');";
			 ResultSet rs1 = s.executeQuery(insert);
			 rs1.close();

			 String sql = "select * from auction where auction_id = " + auctionID;
			 ResultSet rs = s.executeQuery(sql);
			 if(!rs.next()) {
			 	rs.close();
			 	return "failure";
			 }
	
		      rs.close();

			 	}
			 	catch(Exception e) {
			 	System.out.println(e);
			 	}
		 }



		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		/* Sample data begins */

		return "success";
		/* Sample data ends */
	}

	public List getAuctionData(String auctionID, String itemID) {
		
		List<List<?>> output = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		List<Bid> bids = new ArrayList<>();
		List<Auction> auctions = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The item details are required to be encapsulated as a "Item" class object
		 * The bid details are required to be encapsulated as a "Bid" class object
		 * The auction details are required to be encapsulated as a "Auction" class object
		 * The customer details are required to be encapsulated as a "Customer" class object
		 * Query to get data about auction indicated by auctionID and itemID should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter
		 * The customer details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by itemID
		 * The auction details must include details about the item, indicated by auctionID
		 * All the objects must be added in the "output" list and returned
		 */
		
		/*Sample data begins*/

		 try {


		 	 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			 String sql = "select * from auction where auction_id = " +auctionID;
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 Auction auction = new Auction();
				 auction.setAuctionID(rs.getInt("auction_id"));
				 auction.setBidIncrement(rs.getFloat("bid_increment"));
				 auction.setMinimumBid(rs.getFloat("minimum_bid"));
				 auction.setCopiesSold(rs.getInt("copies_sold"));
				 auction.setItemID(rs.getInt("item_id"));
				 auction.setClosingBid(rs.getInt("current_bid"));
				 auction.setCurrentBid(rs.getInt("current_bid"));
				 auction.setCurrentHighBid(rs.getInt("current_bid"));
				 auction.setReserve(rs.getInt("reserve"));
				 auctions.add(auction);
		      }
		      rs.close();

		    }catch(Exception e) {
			 	System.out.println(e);
			}


		  try {

		  	 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			 String sql = "select * from item where item_id = " + itemID;
			 ResultSet query_results = s.executeQuery(sql);
			 while(query_results.next()){
			 	 Item item = new Item();
				 item.setItemID(query_results.getInt("item_id"));
				 item.setName(query_results.getString("name"));
				 item.setType(query_results.getString("type"));
				 item.setNumCopies(query_results.getInt("num_copies"));
				 item.setDescription(query_results.getString("description"));
				 item.setYearManufactured(query_results.getInt("year_manufactured"));
				 item.setSoldPrice(query_results.getInt("SUM(current_high_bid)"));
				 items.add(item);

		      }
			 query_results.close();

		    }
			 	catch(Exception e) {
			 	System.out.println(e);
			}



			try {

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			 String sql = "select * from bid where customer_id in select customer_id from bid where auction_id = "
			  + auctionID; 
			  
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
				customer.setCreditCard(rs.getString("ccNum"));
				customer.setTelephone(rs.getString("phone"));
				customer.setSSN(rs.getString("ssn"));
				customers.add(customer);

		      }
		      rs.close();

		    }catch(Exception e) {
			 	System.out.println(e);
		    }



		try{


			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			String sql = "select * from auction where auction_id = "
			  + auctionID; 
			  
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
			 	Bid bid = new Bid();

			bid.setAuctionID(rs.getInt("auction_id"));
			bid.setCustomerID(rs.getString("customer_id"));
			bid.setBidTime(rs.getString("bid_time"));
			bid.setBidPrice(rs.getFloat("best_price"));
			bids.add(bid);	
			 

		  }
		      rs.close();


		}catch(Exception e) {
			System.out.println(e);
		}


					
			
			
			
			
	
		
		/*Sample data ends*/
		
		output.add(items);
		output.add(bids);
		output.add(auctions);
		output.add(customers);
		
		return output;

	}

	
}
