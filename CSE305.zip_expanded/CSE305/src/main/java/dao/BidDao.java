package dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import model.Bid;
import model.Customer;

public class BidDao {

	public List<Bid> getBidHistory(String auctionID) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * auctionID, which is the Auction's ID, is given as method parameter
		 * Query to get the bid history of an auction, indicated by auctionID, must be implemented
		 */

		/*Sample data begins*/

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
		
		return bids;
	}

	public List<Bid> getAuctionHistory(String customerID) {
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * auctionID, which is the Auction's ID, is given as method parameter
		 * Query to get the bid history of an auction, indicated by auctionID, must be implemented
		 */

		/*Sample data begins*/

		try{

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			String sql = "select * from bid where customer_id = "
			  + customerID; 
			  
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
		
		return bids;
		
	
	}

	public Bid submitBid(String auctionID, String itemID, Float currentBid, Float maxBid, String customerID) {
		Bid bid = new Bid();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			Statement s = con.createStatement();
			//First insert into person table
			String sql = "insert into bid values ('"+ auctionID +"','" + itemID + "','" + currentBid +"','" + maxBid +"','" + customerID+ "');";
			int updated_rows = s.executeUpdate(sql);
			if (updated_rows == 1)
			{
				//Then insert into customer table
				
				bid.setBidPrice(currentBid);
				bid.setCustomerID(customerID);
				bid.setAuctionID(Integer.parseInt(auctionID));
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				bid.setBidTime(timeStamp);
				return bid;
			}
			
		 }
		 catch(Exception e) {
			 System.out.println(e);
			
		 }
		return null;

	}


	public boolean contains(ArrayList<Bid> list, Bid bid) {
		for(Bid b:list){
			if( b.getCustomerID().equals(bid.getCustomerID()) || b.getAuctionID() == bid.getAuctionID() || b.getBidTime().equals(bid.getBidTime()) ){
				return false;

			}

		}

		return false;

	}

	public List<Bid> getSalesListing(String searchKeyword) {
		
		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database
		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * searchKeyword, which is the search parameter, is given as method parameter
		 * Query to  produce a list of sales by item name or by customer name must be implemented
		 * The item name or the customer name can be searched with the provided searchKeyword
		 */

		/*Sample data begins*/

		try{

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			String sql = "select * from bid where customer_id in " + "select customer_id from customer where customer.first_name "+ 
			" LIKE '%" + searchKeyword + "%'" + " OR customer.last_name LIKE %" + searchKeyword + "%";
//			  + customerID; 
			  
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



		try{

			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quickbid", "root", "password");
			 Statement s = con.createStatement();

			String sql = "select * from bid where auction_id in " + "select * from auction where item_id in select * from item where name"+ 
			" LIKE '%" + searchKeyword + "%'";
//			  + customerID; 
			  
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
		
		return bids;
	}

}
