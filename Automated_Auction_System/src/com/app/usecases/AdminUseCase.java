package com.app.usecases;

import java.sql.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.bean.User;
import com.app.consoleColors.ConsoleColors;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.main.Main;

public class AdminUseCase {

    public static void selectoption() {

        while (true) {

            AdminDao admindao = new AdminDaoImpl();

            System.out.println(
                    ConsoleColors.PURPLE_BOLD + "\n Please select an option to continue:" + ConsoleColors.RESET);

            System.out.println(ConsoleColors.DARK_BLUE
                    + "\n1. Get all buyer details \n2. Get all seller details \n3. Get all product list"
                    + "  \n4. Get ongoing auction details \n5. Create auction \n6. View Auction history / selling report   \n7. Update auction history "
                    + "  \n8. Go to main menu \n9. Exit" + ConsoleColors.RESET);

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<User> buyers = admindao.getAllBuyerDetails();

                        System.out
                                .println(ConsoleColors.GREEN_BOLD + "\n--> List of all buyers:" + ConsoleColors.RESET);

                        buyers.forEach(b -> {
                            System.out.println(ConsoleColors.GREEN + "Userid: " + b.getUserid() + " Username: "
                                    + b.getUsername() + " Type: "
                                    + b.getType() + ConsoleColors.RESET);
                        });

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 2:

                    try {

                        List<User> sellers = admindao.getAllSellerDetails();

                        System.out
                                .println(ConsoleColors.GREEN_BOLD + "\n--> List of all sellers:" + ConsoleColors.RESET);

                        sellers.forEach(b -> {
                            System.out.println(ConsoleColors.GREEN + "Userid: " + b.getUserid() + " Username: "
                                    + b.getUsername() + " Type: "
                                    + b.getType() + ConsoleColors.RESET);
                        });

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 3:

                    try {

                        List<Product> productlist = admindao.getAllProductList();

                        System.out.println(
                                ConsoleColors.GREEN_BOLD + "\n--> List of all products:" + ConsoleColors.RESET);

                        productlist.forEach(p -> {
                            System.out.println(ConsoleColors.GREEN + "Prodid: " + p.getProdid() + " Name: "
                                    + p.getName() + " Category: "
                                    + p.getCategory() + " MinPrice: " + p.getMinprice()
                                    + " Quantity: " + p.getQuantity() + " Sellerid: " + p.getSellerid()
                                    + ConsoleColors.RESET);
                        });

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 4:

                    try {

                        List<Auction> auctionlist = admindao.getAuctionDetails();

                        System.out.println(
                                ConsoleColors.GREEN_BOLD + "\n--> List of all ongoing actions:" + ConsoleColors.RESET);

                        auctionlist.forEach(a -> {
                            System.out.println(ConsoleColors.GREEN + "Auctionid: " + a.getAuctionid() + " Sellerid: "
                                    + a.getSellerid()
                                    + " Productid: " + a.getProdid() +
                                    " Startdate: " + a.getStartdate() + " Enddate: " + a.getEnddate() + " Bid value: "
                                    + a.getPrice() + " Buyerid: " + a.getBuyerid() + ConsoleColors.RESET);
                        });

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 5:

                    try {
                        System.out.println(ConsoleColors.ORANGE + "Enter sellerid" + ConsoleColors.RESET);
                        int sellerid = sc.nextInt();

                        System.out.println(ConsoleColors.ORANGE + "Enter productid" + ConsoleColors.RESET);
                        int prodid = sc.nextInt();

                        sc.nextLine();
                        System.out.println(
                                ConsoleColors.ORANGE + "Enter start date in yyyy-mm-dd format" + ConsoleColors.RESET);
                        String inputdate = sc.nextLine();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate sd = LocalDate.parse(inputdate, dtf);
                        Date startdate = Date.valueOf(sd);

                        System.out.println(
                                ConsoleColors.ORANGE + "Enter end date in yyyy-mm-dd format" + ConsoleColors.RESET);
                        String inputdate2 = sc.nextLine();
                        LocalDate ed = LocalDate.parse(inputdate2, dtf);
                        Date enddate = Date.valueOf(ed);

                        System.out.println(ConsoleColors.ORANGE + "Enter price" + ConsoleColors.RESET);
                        float price = sc.nextFloat();

                        String result = admindao.createAuction(sellerid, prodid, startdate, enddate, price, 0);

                        System.out.println(ConsoleColors.GREEN + result + ConsoleColors.RESET);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 6:

                    try {

                        List<AuctionHistory> auctionhistory = admindao.getAllSellingReport();

                        System.out
                                .println(ConsoleColors.GREEN_BOLD + "\n--> All auction history" + ConsoleColors.RESET);

                        auctionhistory.forEach(b -> {
                            System.out.println(ConsoleColors.GREEN + "Auctionid: " + b.getAuctionid() + " Buyerid: "
                                    + b.getBuyerid()
                                    + " Sellerid: " + b.getSellerid() + " WinningBid: " + b.getWinningbid()
                                    + " AuctionDate: " + b.getAuctiondate() + ConsoleColors.RESET);
                        });

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }
                    break;

                case 7:

                    try {
                        String result = admindao.updateAuctionHistory();

                        System.out.println(ConsoleColors.GREEN + result + ConsoleColors.RESET);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 8:
                    Main.run();
                    break;

                case 9: {
                    System.out.println(ConsoleColors.ROSY_PINK_BACKGROUND + "\n--> Thank You !" + ConsoleColors.RESET);
                    System.exit(0);
                }
                    break;

                default:
                    System.out
                            .println(ConsoleColors.RED_BOLD + "\n--> please Enter Valid input." + ConsoleColors.RESET);

            }

        }
    }

}
