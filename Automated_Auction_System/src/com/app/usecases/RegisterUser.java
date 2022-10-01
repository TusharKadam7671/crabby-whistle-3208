package com.app.usecases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.app.bean.Buyer;
import com.app.bean.Seller;
import com.app.bean.Type;
import com.app.exception.SellerException;
import com.app.main.Main;
import com.app.utility.DBUtil;

public class RegisterUser {

    public static void registerUser() {

        try {

            System.out.println("\nPlease select an option to continue:");
            System.out.println("\n1. Register as a Seller \n2. Register as a Buyer"
                    + "\n3. Go to main menu \n4. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try (Connection conn = DBUtil.provideConnection()) {

                        System.out.println("Enter username");
                        String username = sc.next();
                        System.out.println("Enter password");
                        String password = sc.next();
                        String type = "SELLER";

                        PreparedStatement ps1 = conn.prepareStatement(
                                "select * from user where username = ? AND password = ? AND type='SELLER'");

                        ps1.setString(1, username);
                        ps1.setString(2, password);

                        ResultSet rs = ps1.executeQuery();

                        if (rs.next()) {
                            int id = rs.getInt("userid");
                            String name = rs.getString("username");
                            String pass = rs.getString("password");
                            Type type1 = Type.SELLER;
                            Seller seller = new Seller(id, name, pass, type1);

                            System.out.println("\n--> User present already..");

                            SellerUseCase.selectoption();
                        } else {

                            PreparedStatement ps = conn
                                    .prepareStatement("insert into user (username,password,type) values (?,?,?)");

                            ps.setString(1, username);
                            ps.setString(2, password);
                            ps.setString(3, type);

                            int x = ps.executeUpdate();

                            if (x > 0) {
                                System.out.println("\n--> Seller registered successfully");
                                Main.run();
                            } else {
                                System.out.println("\n--> Seller is not registered");
                            }

                        }

                    } catch (SQLException e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:

                    try (Connection conn = DBUtil.provideConnection()) {

                        System.out.println("Enter username");
                        String username = sc.next();
                        System.out.println("Enter password");
                        String password = sc.next();
                        String type = "BUYER";

                        PreparedStatement ps1 = conn.prepareStatement(
                                "select * from user where username = ? AND password = ? AND type='BUYER'");

                        ps1.setString(1, username);
                        ps1.setString(2, password);

                        ResultSet rs = ps1.executeQuery();

                        if (rs.next()) {
                            int id = rs.getInt("userid");
                            String name = rs.getString("username");
                            String pass = rs.getString("password");
                            Type type1 = Type.BUYER;
                            Buyer buyer = new Buyer(id, name, pass, type1);

                            System.out.println("\n--> User present already..");

                            BuyerUseCase.selectoption();
                        } else {

                            PreparedStatement ps = conn
                                    .prepareStatement("insert into user (username,password,type) values (?,?,?)");

                            ps.setString(1, username);
                            ps.setString(2, password);
                            ps.setString(3, type);

                            int x = ps.executeUpdate();

                            if (x > 0) {
                                System.out.println("\n--> Buyer registered successfully");

                                Main.run();
                            } else {
                                System.out.println("\n--> Buyer is not registered");
                            }

                        }

                    } catch (SQLException e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    Main.run();
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please enter correct input");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
