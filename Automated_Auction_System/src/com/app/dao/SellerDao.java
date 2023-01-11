package com.app.dao;

import java.util.List;


import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.bean.User;
import com.app.exception.AuctionException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.ProductException;
import com.app.exception.SellerException;

public interface SellerDao {

    public User loginSeller(String username, String password) throws SellerException;

    public List<Product> getAllProductList() throws ProductException;

    public String updateProduct(int prodid, float price, int quantity) throws ProductException;

    public String addproduct(Product product);

    public String removeProduct(int prodid) throws ProductException;

    public List<AuctionHistory> getAllSellingReport() throws AuctionHistoryException;

    public List<Auction> getAuctionDetails() throws AuctionException;

}
