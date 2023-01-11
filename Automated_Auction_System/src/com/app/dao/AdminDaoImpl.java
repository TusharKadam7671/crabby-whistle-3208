package com.app.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.app.bean.Admin;
import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.bean.Type;
import com.app.bean.User;
import com.app.consoleColors.ConsoleColors;
import com.app.exception.AdminException;
import com.app.exception.AuctionException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.BuyerException;
import com.app.exception.ProductException;
import com.app.exception.SellerException;
import com.app.utility.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin loginAdmin(String username, String password) throws AdminException {

        Admin admin = null;

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from admin where username = ? AND password = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String pass = rs.getString("password");

                admin = new Admin(id, name, pass);
            } else {
                throw new AdminException("Invalid Username or Password");
            }

        } catch (SQLException e) {
            throw new AdminException(e.getMessage());
        }

        return admin;
    }

    @Override
    public List<User> getAllBuyerDetails() throws BuyerException {

        List<User> buyers = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from user where type='buyer'");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Type type = Type.valueOf(rs.getString("type"));

                User buyer = new User(id, username, password, type);

                buyers.add(buyer);
            }

        } catch (SQLException e) {

            throw new BuyerException(e.getMessage());
        }

        if (buyers.size() == 0) {
            System.out.println(ConsoleColors.RED_BOLD + buyers.size() + ConsoleColors.RESET);
            throw new BuyerException("NO buyers available");
        }

        return buyers;
    }

    @Override
    public List<User> getAllSellerDetails() throws SellerException {

        List<User> sellers = new ArrayList<>();

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from user where type='seller'");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Type type = Type.valueOf(rs.getString("type"));

                User seller = new User(id, username, password, type);

                sellers.add(seller);
            }

        } catch (SQLException e) {

            throw new SellerException(e.getMessage());
        }

        if (sellers.size() == 0) {

            throw new SellerException("NO buyers available");
        }

        return sellers;
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
    public String createAuction(int sellerid, int prodid, Date startdate, Date enddate, float price, int buyerid)
            throws AuctionException {

        String message = "Not created";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "insert into auction (sellerid,prodid,startdate,enddate,price,buyerid) values(?,?,?,?,?,?)");

            ps.setInt(1, sellerid);
            ps.setInt(2, prodid);
            ps.setDate(3, startdate);
            ps.setDate(4, enddate);
            ps.setFloat(5, price);
            ps.setInt(6, buyerid);

            int x = ps.executeUpdate();

            if (x > 0) {
                message = "Auction created successfully";
            } else {
                throw new AuctionException();
            }

        } catch (SQLException e) {

            System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
        }

        return message;
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
    public String updateAuctionHistory() throws AuctionHistoryException {

        String message = "Not updated";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "Insert into auctionhistory (auctionid,buyerid,sellerid,winningbid,auctiondate)\r\n"
                            + "Select auctionid,buyerid,sellerid,price,enddate from auction where enddate < (select curdate())");

            int x = ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("delete from auction where enddate < (select curdate())");

            int y = ps2.executeUpdate();

            if (x > 0) {
                message = "Auctionhistory updated successfully";
            } else {
                throw new AuctionHistoryException("Nothing to update");
            }

            if (y > 0) {
                System.out.println(ConsoleColors.GREEN + "Auction updated successfully" + ConsoleColors.RESET);
            } else {
                throw new AuctionException("Auction table not updated");
            }

        } catch (SQLException e) {

            System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
        } catch (AuctionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
