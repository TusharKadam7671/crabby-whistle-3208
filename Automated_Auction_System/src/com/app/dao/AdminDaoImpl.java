package com.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.app.bean.Admin;
import com.app.bean.AuctionHistory;
import com.app.bean.Buyer;
import com.app.bean.Type;
import com.app.bean.Seller;
import com.app.exception.AdminException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.BuyerException;
import com.app.exception.SellerException;
import com.app.exception.UserException;
import com.app.utility.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class AdminDaoImpl implements AdminDao{

	@Override
	public  Admin loginAdmin(String username, String password) throws AdminException {
		
		Admin admin = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				
				admin = new Admin(id,name,pass);
			}else
			{
				throw new AdminException("Invalid Username or Password");
			}
			
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		
		
		return admin;
	}

	@Override
	public List<Buyer> getAllBuyerDetails() throws BuyerException {
		
		List<Buyer> buyers = new ArrayList<>();
		
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from user where type='buyer'");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("userid");
				String username = rs.getString("username");
				String password = rs.getString("password");
//				Type type = Type.valueOf(rs.getString("type"));
				
				Type type = Type.valueOf(rs.getString("type"));
				
				Buyer buyer = new Buyer(id,username,password,type);
				
				buyers.add(buyer);
			}
			
		} catch (SQLException e) {
			
			throw new BuyerException(e.getMessage());
		}
		
		if(buyers.size() == 0)
		{
			System.out.println(buyers.size());
			throw new BuyerException("NO buyers available");
		}
		
		return buyers;
	}

	@Override
	public List<Seller> getAllSellerDetails() throws SellerException {

	    List<Seller> sellers = new ArrayList<>();
        
        
        try (Connection conn = DBUtil.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("select * from user where type='seller'");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Type type = Type.valueOf(rs.getString("type"));
                
                Seller seller = new Seller(id,username,password,type);
                
                sellers.add(seller);
            }
            
        } catch (SQLException e) {
            
            throw new SellerException(e.getMessage());
        }
        
        if(sellers.size() == 0)
        {
//            System.out.println(sellers.size());
            throw new SellerException("NO buyers available");
        }
        
        return sellers;
	}

	@Override
	public List<AuctionHistory> getAllSellingReport() throws AuctionHistoryException {

	    List<AuctionHistory> auctionhistory = new ArrayList<>();
	    
	    
	    try (Connection conn = DBUtil.provideConnection()){
	        
	        PreparedStatement ps = conn.prepareStatement("select * from auctionhistory");
	        
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next())
            {
                int auctionid = rs.getInt("auctionid");
                int buyerid = rs.getInt("buyerid");
                int sellerid = rs.getInt("sellerid");
                float winningbid = rs.getFloat("winningbid");
                Date auctiondate = rs.getDate("auctiondate");
                
                AuctionHistory auction = new AuctionHistory(auctionid, buyerid, sellerid, winningbid, auctiondate);
                
                auctionhistory.add(auction);
            }
	        
            
        } catch (SQLException e) {
            
            throw new AuctionHistoryException(e.getMessage());
        }
	    
	    if(auctionhistory.size() == 0 )
	    {
	        throw new AuctionHistoryException("No data available");
	    }
	    
	    return auctionhistory;
	}

}
