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
import com.app.bean.Product;
import com.app.bean.Seller;
import com.app.bean.Type;
import com.app.exception.AdminException;
import com.app.exception.AuctionHistoryException;
import com.app.exception.ProductException;
import com.app.exception.SellerException;
import com.app.utility.DBUtil;

public class SellerDaoImpl implements SellerDao {

    @Override
    public Seller loginSeller(String username, String password) throws SellerException {

        Seller seller = null;

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from user where username = ? AND password = ?");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("username");
                String pass = rs.getString("password");
                Type type = Type.SELLER;
                seller = new Seller(id, name, pass, type);
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
               

                Product product = new Product(prodid, name, category, minprice, quantity);
                

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
        
        try(Connection conn = DBUtil.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("update product set minprice = ?, quantity = ? where prodid = ?");
            
           
            ps.setFloat(1, price);
            ps.setInt(2, quantity);
            ps.setInt(3, prodid);
            
            
            
            int x = ps.executeUpdate();
            
            if(x>0)
            {
                message = "Product updated successfully";
            }  
            else
            {
                throw new ProductException("Product with productId " + prodid + " is not present in the list, please enter correct id");
            }
                
        }
        catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        
        return message;
    }

    @Override
    public String addproduct(Product product) {
           
        String message = "Not added";
        
        
        try (Connection conn = DBUtil.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("insert into product (name,category,minprice,quantity) values (?,?,?,?)");
            
            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setFloat(3, product.getMinprice());
            ps.setInt(4, product.getQuantity());
            
            int x = ps.executeUpdate();
            
            if(x > 0)
            {
                message = "Product added successfully";
            }
            
            
        } catch (SQLException e) {
            
            message = e.getMessage();
        }
        
        return message;
    }

    @Override
    public String removeProduct(int prodid) throws ProductException {
        // TODO Auto-generated method stub
        return null;
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

}
