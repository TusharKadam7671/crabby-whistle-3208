package com.app.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;

import com.app.bean.Type;
import com.app.bean.User;
import com.app.consoleColors.ConsoleColors;
import com.app.exception.AuctionException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.BidException;
import com.app.exception.BuyerException;
import com.app.exception.ProductException;
import com.app.usecases.GetId;
import com.app.utility.DBUtil;

public class BuyerDaoImpl implements BuyerDao {

    @Override
    public User loginBuyer(String username, String password) throws BuyerException {

        User user = null;

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn
                    .prepareStatement("select * from user where username = ? AND password = ? AND type='BUYER'");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                Type type = Type.BUYER;
                user = new User(id, name, pass, type);
            } else {
                throw new BuyerException("Invalid Username or Password");
            }

        } catch (SQLException e) {
            throw new BuyerException(e.getMessage());
        }

        return user;
    }
    
    @Override
    public List<Product> getAllProductList() throws ProductException {

        List<Product> productlist = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from product");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int prodid = rs.getInt("prodid");
                String name = rs.getString("name");
                String category = rs.getString("category");
                float minprice = rs.getFloat("minprice");
                int quantity = rs.getInt("quantity");
                int sellerid = rs.getInt("sellerid");

                Product product = new Product(prodid, name, category, minprice, quantity, sellerid);

                productlist.add(product);
            }

        } catch (SQLException e) {

            throw new ProductException(e.getMessage());
        }

        if (productlist.size() == 0) {
            throw new ProductException("Products are not available");
        }

        return productlist;
    }

    @Override
    public List<Product> getProductDetailsByCategory(String category) throws ProductException {

        List<Product> productlist = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from product where category = ? ");

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int prodid = rs.getInt("prodid");
                String name = rs.getString("name");
                String category1 = rs.getString("category");
                float minprice = rs.getFloat("minprice");
                int quantity = rs.getInt("quantity");
                int sellerid = rs.getInt("sellerid");

                Product product = new Product(prodid, name, category1, minprice, quantity, sellerid);

                productlist.add(product);
            }

        } catch (SQLException e) {

            throw new ProductException(e.getMessage());
        }

        if (productlist.size() == 0) {
            throw new ProductException("Products are not available");
        }

        return productlist;
    }

    @Override
    public List<AuctionHistory> getAllSellingReport() throws AuctionHistoryException {
        List<AuctionHistory> auctionhistory = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from auctionhistory");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

        if (auctionhistory.size() == 0) {
            throw new AuctionHistoryException("No data available");
        }

        return auctionhistory;
    }

    @Override
    public String bidForProduct(int auctionid, int buyerid, float price) throws BidException {

        String message = "Not added";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "Update auction set buyerid=? , price=? Where price < ? And (select enddate where auctionid=?) >= (select curdate()) and auctionid=?");

            ps.setInt(1, buyerid);
            ps.setFloat(2, price);
            ps.setFloat(3, price);
            ps.setInt(4, auctionid);
            ps.setInt(5, auctionid);

            int x = ps.executeUpdate();

            if (x > 0) {
                message = "Bid added successfully";
            } else {
                throw new BidException("Amount is less or auction is over");
            }

        } catch (SQLException e) {

            System.out.println(ConsoleColors.RED_BACKGROUND+ e.getMessage() +ConsoleColors.RESET);

        } catch (BidException e) {
            System.out.println(ConsoleColors.RED_BACKGROUND+ e.getMessage() +ConsoleColors.RESET);
        }

        return message;
    }

    @Override
    public List<Auction> getAuctionDetails() throws AuctionException {

        List<Auction> auctionlist = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from auction");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int auctionid = rs.getInt("auctionid");
                int sellerid = rs.getInt("sellerid");
                int prodid = rs.getInt("prodid");
                Date startdate = rs.getDate("startdate");
                Date enddate = rs.getDate("enddate");
                float price = rs.getFloat("price");
                int buyerid = rs.getInt("buyerid");

                Auction auction = new Auction(auctionid, sellerid, prodid, startdate, enddate, price, buyerid);

                auctionlist.add(auction);
            }

        } catch (SQLException e) {

            throw new AuctionException(e.getMessage());
        }

        if (auctionlist.size() == 0) {
            throw new AuctionException("Sorry! No auction is going on.");
        }

        return auctionlist;
    }

}
