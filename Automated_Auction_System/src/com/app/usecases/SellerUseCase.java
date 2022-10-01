package com.app.usecases;

import java.util.List;
import java.util.Scanner;

import com.app.bean.Auction;
import com.app.bean.AuctionHistory;
import com.app.bean.Buyer;
import com.app.bean.Product;
import com.app.bean.Seller;
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

            System.out.println("\n Please select an option to continue:");
            System.out.println("\n1. Get all product list \n2. Add product "
                    + "\n3. update product \n4. Remove product \n5. Get ongoing auction details \n6. Check Auction history \n7. Go to main menu \n8. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<Product> productlist = sellerdao.getAllProductList();

                        System.out.println("List of all products:");

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
                        System.out.println("Enter product name");
                        String name = sc.next();

                        System.out.println("Enter product category");
                        String category = sc.next();

                        System.out.println("Enter Minimum price");
                        float minprice = sc.nextFloat();

                        System.out.println("Enter quantity");
                        int quantity = sc.nextInt();

                        System.out.println("Enter seller name");
                        String seller = sc.next();

                        int sellerid = GetId.getId(seller);

                        Product product = new Product();
                        product.setName(name);
                        product.setCategory(category);
                        product.setMinprice(minprice);
                        product.setQuantity(quantity);
                        product.setSellerid(sellerid);

                        String result2 = sellerdao.addproduct(product);

                        System.out.println(result2);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    try {
                        System.out.println("Enter product id to update product details");
                        int prodid = sc.nextInt();
                        System.out.println("Enter new minprice");
                        float newminprice = sc.nextFloat();
                        System.out.println("Enter new quantity");
                        int newquantity = sc.nextInt();

                        String result = sellerdao.updateProduct(prodid, newminprice, newquantity);

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    try {
                        System.out.println("Enter product id to remove that product");
                        int prodid = sc.nextInt();

                        String result = sellerdao.removeProduct(prodid);

                        System.out.println(result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:

                    try {

                        List<Auction> auctionlist = sellerdao.getAuctionDetails();

                        System.out.println("List of all ongoing actions:");

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

                case 6:

                    try {

                        List<AuctionHistory> auctionhistory = sellerdao.getAllSellingReport();

                        System.out.println("All auction history");

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
                    Main.run();
                    break;

                case 8: {
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
