package com.uas.ui;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import com.uas.beans.Application;
import com.uas.beans.ProgramScheduled;
import com.uas.exception.LoginException;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.service.MACservice;
import com.uas.service.MACserviceImpl;
import com.uas.validation.ApplicantValidation;

public class MainUI {

	public static void main(String[] args) {
		
		int choice;
		Scanner sc = new Scanner(System.in);
		LoginUI login =new LoginUI();
		ApplicantUI app = new ApplicantUI();


		do {
			System.out.println("===Welcome to University Admission System  ===");
			System.out.println("Enter Choice:");
			System.out.println("1.Do You Want To Apply for a Course");
			System.out.println("2.Track Your Application Status");
			System.out.println("3.Login");
			//System.out.println("3.Administrator");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				app.apply();
				
			

				break;
			case 2:
			
				app.checkStatus();
				break;
			case 3:
				
				login.getLogin();
				
				
				
				
				break;
				
				
			    

			case 4:
				System.exit(0);
				break;
			default:
				break;
			}

		} while (choice != 4);
		System.out.println("Main->end...");
	}
}
