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
import com.app.exception.ProductException;
import com.app.exception.SellerException;
import com.app.utility.DBUtil;

public class SellerDaoImpl implements SellerDao {

    @Override
    public User loginSeller(String username, String password) throws SellerException {

        User seller = null;

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn
                    .prepareStatement("select * from user where username = ? AND password = ? AND type='SELLER'");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                Type type = Type.SELLER;
                seller = new User(id, name, pass, type);
            } else {
                throw new SellerException("Invalid Username or Password");
            }

        } catch (SQLException e) {
            throw new SellerException(e.getMessage());
        }

        return seller;

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
    public String updateProduct(int prodid, float price, int quantity) throws ProductException {

        String message = "Not updated";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn
                    .prepareStatement("update product set minprice = ?, quantity = ? where prodid = ?");

            ps.setFloat(1, price);
            ps.setInt(2, quantity);
            ps.setInt(3, prodid);

            int x = ps.executeUpdate();

            if (x > 0) {
                message = "Product updated successfully";
            } else {
                throw new ProductException(
                        "Product with productId " + prodid + " is not present in the list, please enter correct id");
            }

        } catch (SQLException e) {

            System.out.println(ConsoleColors.RED_BACKGROUND+ e.getMessage() +ConsoleColors.RESET);
        }

        return message;
    }

    @Override
    public String addproduct(Product product) {

        String message = "Not added";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "insert into product (name,category,minprice,quantity,sellerid) values (?,?,?,?,?)");

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setFloat(3, product.getMinprice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getSellerid());

            int x = ps.executeUpdate();

            if (x > 0) {
                message = "Product added successfully";
            } else {
                throw new ProductException(message);
            }

        } catch (SQLException e) {

            System.out.println(ConsoleColors.RED_BACKGROUND+ e.getMessage() +ConsoleColors.RESET);

        } catch (ProductException e) {
            System.out.println(ConsoleColors.RED_BACKGROUND+ e.getMessage() +ConsoleColors.RESET);
        }

        return message;
    }

    @Override
    public String removeProduct(int prodid) throws ProductException {

        String message = "Not removed";

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("delete from product where prodid = ? ");

            ps.setInt(1, prodid);

            int x = ps.executeUpdate();

            if (x > 0) {
                message = "Product removed successfully from the list";
            } else {
                throw new ProductException(message);
            }

        } catch (SQLException e) {

            throw new ProductException("Product is not present in the list with productId " + prodid);
        }

        return message;
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
