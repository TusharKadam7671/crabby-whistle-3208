package com.app.usecases;

import java.util.List;

import java.util.Scanner;

import com.app.bean.AuctionHistory;
import com.app.bean.Buyer;
import com.app.bean.Seller;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.main.Main;

public class AdminUseCase {

    public static void selectoption() {

        while (true) {

            AdminDao admindao = new AdminDaoImpl();

            System.out.println("\n Please select an option to continue:");
            System.out.println("\n1. Get all buyer details \n2. Get all seller details"
                    + "\n3. View Selling report \n4. Go to main menu \n5. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<Buyer> buyers = admindao.getAllBuyerDetails();

                        System.out.println("List of all buyers:");

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

                        System.out.println("List of all sellers:");

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
                        
                        List<AuctionHistory> auctionhistory = admindao.getAllSellingReport();
                        
                        System.out.println("All auction history");
                        
                        auctionhistory.forEach(b -> {
                            System.out.println("Auctionid: "+b.getAuctionid()+" Buyerid: "+b.getBuyerid()+" Sellerid: "+b.getSellerid()+" WinningBid: "+b.getWinningbid()+" AuctionDate: "+b.getAuctiondate());
                        });
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    Main.run();
                    break;

                case 5: {
                    System.out.println("Thank You !");
                    System.exit(0);
                }
                break;

                default:
                    System.out.println("please Enter Valid input.");

            }

        }
    }

}
