package com.app.usecases;

import java.util.List;
import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.consoleColors.ConsoleColors;
import com.app.dao.BuyerDao;
import com.app.dao.BuyerDaoImpl;

import com.app.main.Main;

public class BuyerUseCase {

    public static void selectoption() {

        while (true) {

            BuyerDao buyerdao = new BuyerDaoImpl();

            System.out.println(
                    ConsoleColors.PURPLE_BOLD + "\n Please select an option to continue:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.DARK_BLUE +
                    "\n1. Get all product list \n2. Get product details by category \n3. Get ongoing auction details \n4. Check Auction history "
                    + "\n5. Bid for product \n6. Logout \n7. Exit" + ConsoleColors.RESET);

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                
                case 1:

                    try {

                        List<Product> productlist = buyerdao.getAllProductList();

                        System.out.println(ConsoleColors.GREEN_BOLD + "List of all products:" + ConsoleColors.RESET);

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
                    
                case 2:

                    try {

                        sc.nextLine();
                        System.out
                                .println(ConsoleColors.ORANGE + "Enter category to see products" + ConsoleColors.RESET);
                        String category = sc.nextLine();

                        List<Product> productlist = buyerdao.getProductDetailsByCategory(category);

                        System.out.println(ConsoleColors.GREEN_BOLD + "\n--> List of products:" + ConsoleColors.RESET);

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

                case 3:

                    try {

                        List<Auction> auctionlist = buyerdao.getAuctionDetails();

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
                        System.out.println(ConsoleColors.RESET);
                    }

                    break;

                case 4:

                    try {

                        List<AuctionHistory> auctionhistory = buyerdao.getAllSellingReport();

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

                case 5:

                    try {
                        System.out.println(ConsoleColors.ORANGE + "Enter auction id" + ConsoleColors.RESET);
                        int auctionid = sc.nextInt();

                        sc.nextLine();
                        System.out.println(ConsoleColors.ORANGE + "Enter buyer name" + ConsoleColors.RESET);
                        String username = sc.nextLine();

                        int buyerid = GetId.getId(username);

                        System.out.println(ConsoleColors.ORANGE + "Enter bid value" + ConsoleColors.RESET);
                        float bidvalue = sc.nextFloat();

                        String result2 = buyerdao.bidForProduct(auctionid, buyerid, bidvalue);

                        System.out.println(result2);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 6:
                    Main.run();
                    break;

                case 7: {
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
