package com.app.dao;

import java.sql.Date;

import java.util.List;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.bean.User;
import com.app.exception.AuctionException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.BidException;
import com.app.exception.BuyerException;
import com.app.exception.ProductException;

public interface BuyerDao {

    public User loginBuyer(String username, String password) throws BuyerException;
    
    public List<Product> getAllProductList() throws ProductException;

    public List<Product> getProductDetailsByCategory(String category) throws ProductException;

    public List<AuctionHistory> getAllSellingReport() throws AuctionHistoryException;

    public String bidForProduct(int auctionid, int buyerid, float price) throws BidException;

    public List<Auction> getAuctionDetails() throws AuctionException;

}
