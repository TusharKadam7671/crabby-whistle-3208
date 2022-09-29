package com.app.usecases;

import java.util.List;
import java.util.Scanner;

import com.app.bean.Buyer;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;

public class AdminUseCase {
	
	public static void selectoption()
	{
		
		while(true)
		{
			System.out.println("\n Please select an option to continue:");
			System.out.println("\n1. Get all buyer details \n2. Get all seller details"
					+ "\n3. View Selling report \n4. Exit");
			
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				
				AdminDao admindao = new AdminDaoImpl();
				
				try {
					
					List<Buyer> buyers = admindao.getAllBuyerDetails();
					
					System.out.println("List of all buyers:");
					
					buyers.forEach( b -> {
						System.out.println("Userid: "+b.getUserid()+" Username: "+b.getUsername()+" Type: "+b.getType());
					});
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}
			
		}
	}

}
