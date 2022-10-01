package com.app.main;

import java.util.Scanner;

import com.app.bean.Admin;
import com.app.bean.Buyer;
import com.app.bean.Seller;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.dao.BuyerDao;
import com.app.dao.BuyerDaoImpl;
import com.app.dao.SellerDao;
import com.app.dao.SellerDaoImpl;
import com.app.exception.AdminException;
import com.app.exception.BuyerException;
import com.app.exception.SellerException;
import com.app.usecases.AdminUseCase;
import com.app.usecases.BuyerUseCase;
import com.app.usecases.RegisterUser;
import com.app.usecases.SellerUseCase;

public class Main {

    public static void run() {

        try {

            System.out.println("\nPlease select an option to continue:");
            System.out.println("\n1. Login as Admin \n2. Register as Buyer or Seller"
                    + " \n3. Login as Seller \n4. Login as Buyer  \n5. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    System.out.println("Enter username:");
                    String username = sc.next();
                    System.out.println("Enter password:");
                    String password = sc.next();

                    AdminDao admindao = new AdminDaoImpl();

                    try {

                        Admin admin = admindao.loginAdmin(username, password);

                        System.out.println("\n--> Welcome Admin: " + admin.getUsername());

                        AdminUseCase.selectoption();

                    } catch (AdminException e) {
                        System.out.println(e.getMessage());
                        run();
                    }

                    break;

                case 2:
                    RegisterUser.registerUser();
                    break;

                case 3:

                    System.out.println("Enter username:");
                    String Susername = sc.next();
                    System.out.println("Enter password:");
                    String Spassword = sc.next();

                    SellerDao sellerdao = new SellerDaoImpl();

                    try {

                        Seller seller = sellerdao.loginSeller(Susername, Spassword);

                        System.out.println("\n--> Welcome Seller: " + seller.getUsername());

                        SellerUseCase.selectoption();

                    } catch (SellerException e) {
                        System.out.println(e.getMessage());
                        run();
                    }

                    break;

                case 4:

                    System.out.println("Enter username:");
                    String Busername = sc.next();
                    System.out.println("Enter password:");
                    String Bpassword = sc.next();

                    BuyerDao buyerdao = new BuyerDaoImpl();

                    try {

                        Buyer buyer = buyerdao.loginBuyer(Busername, Bpassword);

                        System.out.println("\n--> Welcome Buyer: " + buyer.getUsername());

                        BuyerUseCase.selectoption();

                    } catch (BuyerException e) {
                        System.out.println(e.getMessage());
                        run();
                    }

                    break;

                case 5: {
                    System.out.println("\nThank You !");
                    System.exit(0);
                }

                    break;

                default:
                    System.out.println("please Enter Valid input.");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("      ***********-----------***********      ");
        System.out.println("     Welcome to Automated Auction System     ");
        System.out.println("      ***********-----------***********      ");

        run();

    }

}
