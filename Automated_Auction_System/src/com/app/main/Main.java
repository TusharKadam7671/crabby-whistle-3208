package com.app.main;

import java.util.Scanner;

import com.app.bean.Admin;
import com.app.dao.AdminDao;
import com.app.dao.AdminDaoImpl;
import com.app.exception.AdminException;
import com.app.usecases.AdminUseCase;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to Automated Auction System");
		
		try {
			
			System.out.println("\nPlease select an option to contunue:");
			System.out.println("\n1. Login as Admin \n2. Register as Buyer or Seller"
					+ "\n3. Login as Buyer \n4. Login as Seller");
			
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				
				System.out.println("Enter username:");
				String username = sc.next();
				System.out.println("Enter password:");
				String password = sc.next();
				
				AdminDao admindao = new AdminDaoImpl();
				
				try {
					
					Admin admin = admindao.loginAdmin(username, password);
					
					System.out.println("Welcome Admin: "+admin.getUsername());
					
					AdminUseCase.selectoption();
					
					
				} catch (AdminException e) {
					System.out.println(e.getMessage());
				}
				
				
			}
			
		} catch (Exception e) {
			
		}
		
		

	}

}
