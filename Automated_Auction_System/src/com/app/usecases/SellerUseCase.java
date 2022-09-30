package com.app.usecases;

import java.util.List;
import java.util.Scanner;

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

            AdminDao admindao = new AdminDaoImpl();
            SellerDao sellerdao = new SellerDaoImpl();

            System.out.println("\n Please select an option to continue:");
            System.out.println("\n1. Get all product list \n2. update product"
                    + "\n3. Add product \n4. Remove product \n5. Check Auction history \n6. Go to main menu \n7. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    try {

                        List<Product> productlist = sellerdao.getAllProductList();

                        System.out.println("List of all products:");

                        productlist.forEach(p -> {
                            System.out.println("Prodid: "+p.getProdid() +" Name: "+p.getName() + " Category: "+p.getCategory() + " MinPrice: "+p.getMinprice()
                            +" Quantity: "+p.getQuantity());
                        });

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                    
                

                case 2:

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
                    
                case 3:
                    
                    System.out.println("Enter product name");
                    String name = sc.next();
                    
                    System.out.println("Enter product category");
                    String category = sc.next();
                    
                    System.out.println("Enter Minimum price");
                    float minprice = sc.nextFloat();
                    
                    System.out.println("Enter quantity");
                    int quantity = sc.nextInt();
                    
                    Product product = new Product();
                    product.setName(name);
                    product.setCategory(category);
                    product.setMinprice(minprice);
                    product.setQuantity(quantity);
                    
                    
                    String result2 = sellerdao.addproduct(product);
                    
                    System.out.println(result2);
                    
                    
                    
                    break;

                case 5:
                    
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

                case 6:
                    Main.run();
                    break;

                case 7: {
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
