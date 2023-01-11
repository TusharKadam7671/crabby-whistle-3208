package com.app.usecases;

import java.util.List;

import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Product;
import com.app.consoleColors.ConsoleColors;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.dao.SellerDao;
import com.app.dao.SellerDaoImpl;
import com.app.exception.ProductException;
import com.app.main.Main;
import com.app.utility.DBUtil;

public class SellerUseCase {

    public static void selectoption() {

        while (true) {

            SellerDao sellerdao = new SellerDaoImpl();

            System.out.println(
                    ConsoleColors.PURPLE_BOLD + "\n Please select an option to continue:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.DARK_BLUE + "\n1. Get all product list \n2. Add product "
                    + "\n3. update product \n4. Remove product \n5. Get ongoing auction details \n6. Check Auction history \n7. Logout \n8. Exit"
                    + ConsoleColors.RESET);

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<Product> productlist = sellerdao.getAllProductList();

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
                        System.out.println(ConsoleColors.ORANGE + "Enter product name" + ConsoleColors.RESET);
                        String name = sc.nextLine();

                        System.out.println(ConsoleColors.ORANGE + "Enter product category" + ConsoleColors.RESET);
                        String category = sc.nextLine();

                        System.out.println(ConsoleColors.ORANGE + "Enter Minimum price" + ConsoleColors.RESET);
                        float minprice = sc.nextFloat();

                        System.out.println(ConsoleColors.ORANGE + "Enter quantity" + ConsoleColors.RESET);
                        int quantity = sc.nextInt();

                        sc.nextLine();
                        System.out.println(ConsoleColors.ORANGE + "Enter seller name" + ConsoleColors.RESET);
                        String seller = sc.nextLine();

                        int sellerid = GetId.getId(seller);

                        Product product = new Product();
                        product.setName(name);
                        product.setCategory(category);
                        product.setMinprice(minprice);
                        product.setQuantity(quantity);
                        product.setSellerid(sellerid);

                        String result2 = sellerdao.addproduct(product);

                        System.out.println(ConsoleColors.GREEN + result2 + ConsoleColors.RESET);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 3:

                    try {
                        System.out.println(ConsoleColors.ORANGE + "Enter product id to update product details"
                                + ConsoleColors.RESET);
                        int prodid = sc.nextInt();
                        System.out.println(ConsoleColors.ORANGE + "Enter new minprice" + ConsoleColors.RESET);
                        float newminprice = sc.nextFloat();
                        System.out.println(ConsoleColors.ORANGE + "Enter new quantity" + ConsoleColors.RESET);
                        int newquantity = sc.nextInt();

                        String result = sellerdao.updateProduct(prodid, newminprice, newquantity);

                        System.out.println(ConsoleColors.GREEN + result + ConsoleColors.RESET);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 4:

                    try {
                        System.out.println(
                                ConsoleColors.ORANGE + "Enter product id to remove that product" + ConsoleColors.RESET);
                        int prodid = sc.nextInt();

                        String result = sellerdao.removeProduct(prodid);

                        System.out.println(ConsoleColors.GREEN + result + ConsoleColors.RESET);

                    } catch (Exception e) {
                        System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
                    }

                    break;

                case 5:

                    try {

                        List<Auction> auctionlist = sellerdao.getAuctionDetails();

                        System.out.println(
                                ConsoleColors.GREEN_BOLD + "List of all ongoing actions:" + ConsoleColors.RESET);

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

                case 6:

                    try {

                        List<AuctionHistory> auctionhistory = sellerdao.getAllSellingReport();

                        System.out.println(ConsoleColors.GREEN_BOLD + "All auction history" + ConsoleColors.RESET);

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
                    Main.run();
                    break;

                case 8: {
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
