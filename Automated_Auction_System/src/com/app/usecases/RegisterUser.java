package com.app.usecases;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.app.bean.Type;
import com.app.bean.User;
import com.app.consoleColors.ConsoleColors;
import com.app.exception.SellerException;
import com.app.main.Main;
import com.app.utility.DBUtil;

public class RegisterUser {

    public static void registerUser() {

        try {

            System.out.println(
                    ConsoleColors.PURPLE_BOLD + "\nPlease select an option to continue:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.DARK_BLUE + "\n1. Register as a Seller \n2. Register as a Buyer"
                    + "\n3. Go to main menu \n4. Exit" + ConsoleColors.RESET);

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try (Connection conn = DBUtil.provideConnection()) {

                        sc.nextLine();
                        System.out.println(ConsoleColors.ORANGE + "Enter username" + ConsoleColors.RESET);
                        String username = sc.nextLine();

                        System.out.println(ConsoleColors.ORANGE + "Enter password" + ConsoleColors.RESET);
                        String password = sc.nextLine();
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
                            User seller = new User(id, name, pass, type1);

                            System.out.println(
                                    ConsoleColors.GREEN + "\n--> User present already.." + ConsoleColors.RESET);

                            SellerUseCase.selectoption();
                        } else {

                            PreparedStatement ps = conn
                                    .prepareStatement("insert into user (username,password,type) values (?,?,?)");

                            ps.setString(1, username);
                            ps.setString(2, password);
                            ps.setString(3, type);

                            int x = ps.executeUpdate();

                            if (x > 0) {
                                System.out.println(ConsoleColors.GREEN + "\n--> Seller registered successfully"
                                        + ConsoleColors.RESET);
                                Main.run();
                            } else {
                                System.out.println(ConsoleColors.RED_BOLD + "\n--> Seller is not registered"
                                        + ConsoleColors.RESET);
                            }

                        }

                    } catch (SQLException e) {
                        
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 2:

                    try (Connection conn = DBUtil.provideConnection()) {

                        sc.nextLine();
                        System.out.println(ConsoleColors.ORANGE + "Enter username" + ConsoleColors.RESET);
                        String username = sc.nextLine();
                        System.out.println(ConsoleColors.ORANGE + "Enter password" + ConsoleColors.RESET);
                        String password = sc.nextLine();
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
                            User buyer = new User(id, name, pass, type1);

                            System.out.println(
                                    ConsoleColors.GREEN + "\n--> User present already.." + ConsoleColors.RESET);

                            BuyerUseCase.selectoption();
                        } else {

                            PreparedStatement ps = conn
                                    .prepareStatement("insert into user (username,password,type) values (?,?,?)");

                            ps.setString(1, username);
                            ps.setString(2, password);
                            ps.setString(3, type);

                            int x = ps.executeUpdate();

                            if (x > 0) {
                                System.out.println(ConsoleColors.GREEN + "\n--> Buyer registered successfully"
                                        + ConsoleColors.RESET);

                                Main.run();
                            } else {
                                System.out.println(
                                        ConsoleColors.RED_BOLD + "\n--> Buyer is not registered" + ConsoleColors.RESET);
                            }

                        }

                    } catch (SQLException e) {
                        
                        System.out.println(ConsoleColors.RESET);
                    }

                    break;

                case 3:
                    Main.run();
                    break;

                case 4:
                    System.out.println(ConsoleColors.ROSY_PINK_BACKGROUND + "\nThank You !" + ConsoleColors.RESET);
                    System.exit(0);
                    break;

                default:
                    System.out
                            .println(ConsoleColors.RED_BACKGROUND + "Please enter correct input" + ConsoleColors.RESET);
            }

        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
        }
    }

}
