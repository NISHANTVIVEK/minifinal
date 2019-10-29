package com.uas.ui;

import java.util.Scanner;

import com.uas.beans.Application;
import com.uas.beans.Program;
import com.uas.service.AdminService;
import com.uas.service.AdminServiceImpl;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.service.MACservice;
import com.uas.service.MACserviceImpl;

public class AdminUI {

	public void getAdminUI() {
		
		MACservice mac = new MACserviceImpl();
		//ApplicationDAO applicant = new ApplicationDAOImpl();
		ApplicationService applicant = new ApplicationServiceImpl();
		Program prg =new Program();
		Scanner sc =new Scanner(System.in);
		
		String ch =null;
		
		do{
		
		AdminService service =new AdminServiceImpl();
		int choice1 = 0;
		String p=null;
		
		System.out.println("Enter the operation you want to perform");
		System.out.println("1.List of Programs Offered");
		System.out.println("2.List of Applicant Details");
		System.out.println("3.Add Program");
		System.out.println("4.Delete Program");
		choice1 = sc.nextInt();
		switch(choice1)
		{
		case 1:
		{
			System.out.println("--------List of Available Programs---------\n");
		
			System.out.println(applicant.showDetails());
			break;
		}
		case 2:
		{
			System.out.println("--------List of Applicants---------\n");
			mac.showDetails();
			break;
		}
		case 3:
		{
			
			
			service.addDetails(prg);	
			break;
			
		}
		case 4:
		{
			System.out.println("--------Delete Program---------\n");
			
		
			System.out.println("Enter the program to be deleted: ");
			System.out.println("choose 1 for Delete from Programs Offered:");
			System.out.println("choose 2 for Delete from Programs Scheduled:");
			int choice= sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("--------List of Programs-------");
				System.out.println(applicant.showDetails());
				System.out.println();
				System.out.println("Enter the program to be deleted: ");
				p = sc.next();
				service.deleteDetails(p);
				break;
			case 2:
				System.out.println("--------List of Programs-------");
				System.out.println(applicant.showScheduledDetails());
				System.out.println();
				System.out.println("Enter the program to be deleted: ");
				p = sc.next();
				service.deleteScheduledDetails(p);
				break;
				default:
					break;
			}
		
		}
		}
		System.out.println("Do You Want To Continue");
		ch=sc.next();
	}while(ch.equals("y"));
	}
}
