package com.app.dao;

import java.sql.Date;


import java.util.List;

import com.app.bean.Admin;
import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.bean.User;
import com.app.exception.AdminException;
import com.app.exception.AuctionException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.BuyerException;
import com.app.exception.ProductException;
import com.app.exception.SellerException;

public interface AdminDao {

    public Admin loginAdmin(String username, String password) throws AdminException;

    public List<User> getAllBuyerDetails() throws BuyerException;

    public List<User> getAllSellerDetails() throws SellerException;

    public List<Product> getAllProductList() throws ProductException;

    public String createAuction(int sellerid, int prodid, Date startdate, Date enddate, float price, int buyerid)
            throws AuctionException;

    public List<Auction> getAuctionDetails() throws AuctionException;

    public String updateAuctionHistory() throws AuctionHistoryException;

    public List<AuctionHistory> getAllSellingReport() throws AuctionHistoryException;


}
