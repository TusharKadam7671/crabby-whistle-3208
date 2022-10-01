package com.app.usecases;

import java.util.List;
import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;

import com.app.dao.BuyerDao;
import com.app.dao.BuyerDaoImpl;

import com.app.main.Main;

public class BuyerUseCase {

    public static void selectoption() {

        while (true) {

            BuyerDao buyerdao = new BuyerDaoImpl();

            System.out.println("\n Please select an option to continue:");
            System.out.println(
                    "\n1. Get product details by category \n2. Get ongoing auction details \n3. Check Auction history "
                            + "\n4. Bid for product \n5. Go to main menu \n6. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        System.out.println("Enter category to see products");
                        String category = sc.next();

                        List<Product> productlist = buyerdao.getProductDetailsByCategory(category);

                        System.out.println("\n--> List of products:");

                        productlist.forEach(p -> {
                            System.out.println("Prodid: " + p.getProdid() + " Name: " + p.getName() + " Category: "
                                    + p.getCategory() + " MinPrice: " + p.getMinprice()
                                    + " Quantity: " + p.getQuantity() + " Sellerid: " + p.getSellerid());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:

                    try {

                        List<Auction> auctionlist = buyerdao.getAuctionDetails();

                        System.out.println("\n--> List of all ongoing actions:");

                        auctionlist.forEach(a -> {
                            System.out.println("Auctionid: " + a.getAuctionid() + " Sellerid: " + a.getSellerid()
                                    + " Productid: " + a.getProdid() +
                                    " Startdate: " + a.getStartdate() + " Enddate: " + a.getEnddate() + " Bid value: "
                                    + a.getPrice() + " Buyerid: " + a.getBuyerid());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    try {

                        List<AuctionHistory> auctionhistory = buyerdao.getAllSellingReport();

                        System.out.println("\n--> All auction history");

                        auctionhistory.forEach(b -> {
                            System.out.println("Auctionid: " + b.getAuctionid() + " Buyerid: " + b.getBuyerid()
                                    + " Sellerid: " + b.getSellerid() + " WinningBid: " + b.getWinningbid()
                                    + " AuctionDate: " + b.getAuctiondate());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:

                    try {
                        System.out.println("Enter auction id");
                        int auctionid = sc.nextInt();

                        System.out.println("Enter buyer name");
                        String username = sc.next();

                        int buyerid = GetId.getId(username);

                        System.out.println("Enter bid value");
                        float bidvalue = sc.nextFloat();

                        String result2 = buyerdao.bidForProduct(auctionid, buyerid, bidvalue);

                        System.out.println(result2);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    Main.run();
                    break;

                case 6: {
                    System.out.println("\nThank You !");
                    System.exit(0);
                }
                    break;

                default:
                    System.out.println("please Enter Valid input.");

            }

        }

    }

}
