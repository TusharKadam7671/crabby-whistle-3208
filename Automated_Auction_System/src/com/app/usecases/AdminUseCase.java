package com.app.usecases;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Buyer;
import com.app.bean.Product;
import com.app.bean.Seller;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.main.Main;

public class AdminUseCase {

    public static void selectoption() {

        while (true) {

            AdminDao admindao = new AdminDaoImpl();

            System.out.println("\n Please select an option to continue:");
            System.out.println("\n1. Get all buyer details \n2. Get all seller details \n3. Get all product list"
                    + "  \n4. Get ongoing auction details \n5. Create auction \n6. View Auction history / selling report   \n7. Update auction history "
                    + "  \n8. Go to main menu \n9. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<Buyer> buyers = admindao.getAllBuyerDetails();

                        System.out.println("\n--> List of all buyers:");

                        buyers.forEach(b -> {
                            System.out.println("Userid: " + b.getUserid() + " Username: " + b.getUsername() + " Type: "
                                    + b.getType());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:

                    try {

                        List<Seller> sellers = admindao.getAllSellerDetails();

                        System.out.println("\n--> List of all sellers:");

                        sellers.forEach(b -> {
                            System.out.println("Userid: " + b.getUserid() + " Username: " + b.getUsername() + " Type: "
                                    + b.getType());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    try {

                        List<Product> productlist = admindao.getAllProductList();

                        System.out.println("\n--> List of all products:");

                        productlist.forEach(p -> {
                            System.out.println("Prodid: " + p.getProdid() + " Name: " + p.getName() + " Category: "
                                    + p.getCategory() + " MinPrice: " + p.getMinprice()
                                    + " Quantity: " + p.getQuantity() + " Sellerid: " + p.getSellerid());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    try {

                        List<Auction> auctionlist = admindao.getAuctionDetails();

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

                case 5:

                    try {
                        System.out.println("Enter sellerid");
                        int sellerid = sc.nextInt();

                        System.out.println("Enter productid");
                        int prodid = sc.nextInt();

                        System.out.println("Enter start date in yyyy-mm-dd format");
                        String inputdate = sc.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate sd = LocalDate.parse(inputdate, dtf);
                        Date startdate = Date.valueOf(sd);

                        System.out.println("Enter end date in yyyy-mm-dd format");
                        String inputdate2 = sc.next();
                        LocalDate ed = LocalDate.parse(inputdate2, dtf);
                        Date enddate = Date.valueOf(ed);

                        System.out.println("Enter price");
                        float price = sc.nextFloat();

                        String result = admindao.createAuction(sellerid, prodid, startdate, enddate, price, 0);

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:

                    try {

                        List<AuctionHistory> auctionhistory = admindao.getAllSellingReport();

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

                case 7:

                    try {
                        String result = admindao.updateAuctionHistory();

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 8:
                    Main.run();
                    break;

                case 9: {
                    System.out.println("\n--> Thank You !");
                    System.exit(0);
                }
                    break;

                default:
                    System.out.println("\n--> please Enter Valid input.");

            }

        }
    }

}
