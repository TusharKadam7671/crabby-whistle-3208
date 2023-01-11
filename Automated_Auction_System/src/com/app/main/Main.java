package com.app.main;

import java.util.Scanner;

import com.app.bean.Admin;
import com.app.bean.User;
import com.app.consoleColors.ConsoleColors;
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

            System.out.println(
                    ConsoleColors.PURPLE_BOLD + "\nPlease select an option to continue:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.DARK_BLUE + "\n1. Login as Admin \n2. Register as Buyer or Seller"
                    + " \n3. Login as Seller \n4. Login as Buyer  \n5. Exit" + ConsoleColors.RESET);

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter username:" + ConsoleColors.RESET);
                    String username = sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter password:" + ConsoleColors.RESET);
                    String password = sc.nextLine();

                    AdminDao admindao = new AdminDaoImpl();

                    try {

                        Admin admin = admindao.loginAdmin(username, password);

                        System.out.println(ConsoleColors.GREEN + "\n--> Welcome Admin: " + admin.getUsername()
                                + ConsoleColors.RESET);

                        AdminUseCase.selectoption();

                    } catch (AdminException e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                        run();
                    }

                    break;

                case 2:
                    RegisterUser.registerUser();
                    break;

                case 3:

                    sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter username:" + ConsoleColors.RESET);
                    String Susername = sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter password:" + ConsoleColors.RESET);
                    String Spassword = sc.nextLine();

                    SellerDao sellerdao = new SellerDaoImpl();

                    try {

                        User seller = sellerdao.loginSeller(Susername, Spassword);

                        System.out.println(ConsoleColors.GREEN + "\n--> Welcome Seller: " + seller.getUsername()
                                + ConsoleColors.RESET);

                        SellerUseCase.selectoption();

                    } catch (SellerException e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                        run();
                    }

                    break;

                case 4:

                    sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter username:" + ConsoleColors.RESET);
                    String Busername = sc.nextLine();
                    System.out.println(ConsoleColors.ORANGE + "Enter password:" + ConsoleColors.RESET);
                    String Bpassword = sc.nextLine();

                    BuyerDao buyerdao = new BuyerDaoImpl();

                    try {

                        User buyer = buyerdao.loginBuyer(Busername, Bpassword);

                        System.out.println(ConsoleColors.GREEN + "\n--> Welcome Buyer: " + buyer.getUsername()
                                + ConsoleColors.RESET);

                        BuyerUseCase.selectoption();

                    } catch (BuyerException e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                        run();
                    }

                    break;

                case 5: {
                    System.out.println(ConsoleColors.ROSY_PINK_BACKGROUND + "\nThank You !" + ConsoleColors.RESET);
                    System.exit(0);
                }

                    break;

                default:
                    System.out.println(ConsoleColors.RED_BOLD + "please Enter Valid input." + ConsoleColors.RESET);
                    run();

            }

        } catch (Exception e) {

            System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);

        }

    }

    public static void main(String[] args) {

        System.out.println(ConsoleColors.CYAN_BACKGROUND + "      ***********-----------***********      ");
        System.out.println("     Welcome to Automated Auction System     ");
        System.out.println("      ***********-----------***********      " + ConsoleColors.RESET);

        run();

    }

}
