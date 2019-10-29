package com.uas.ui;

import java.util.Scanner;

import com.uas.service.MACservice;
import com.uas.service.MACserviceImpl;

public class MacUI {

	public void getMacUI() {
		MACservice mac = new MACserviceImpl();
		Scanner sc = new Scanner(System.in);
		mac.showDetails();
		String ch;
		do {
			System.out.println("Enter 1 for updating the status of candidate and setting up an interview date. ");
			System.out.println("Enter 2 for confirming or rejecting the application");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("select id");
				int idd = sc.nextInt();
				String status = mac.showStatus(idd);
				System.out.println(status);
				if (status.equals("applied")) {
					System.out.println("Enter 1 to accept the candidate:");
					System.out.println("Enter 2 to reject the candidate:");
					int choice1 = sc.nextInt();
					if (choice1 == 1) {
						mac.updateStatus(idd, "accepted");
						status = "accepted";

						System.out.println("show updated table.................");
						mac.showDetails();

					}
					if (choice1 == 2) {
						mac.updateStatus(idd, "rejected");
						status = "rejected";
						System.out.println("show updated table.................");
						mac.showDetails();

					}
				}
				if (status.equals("accepted")) {
					System.out.println("Status of the candidate is already 'accepted', waiting for confirmation");
					System.out.println("show updated table.................");
					mac.showDetails();

				}
				if (status.equals("confirmed")) {
					System.out.println("Status of the candidate can not be changed.");
					System.out.println("show updated table.................");
					mac.showDetails();

				}

				break;
			}
			case 2: {
				System.out.println("select id");
				int idd = sc.nextInt();
				String status = mac.showStatus(idd);
				if (status.equals("applied")) {
					System.out.println(
							"Status of the candidate can not be changed directly to confirmation without interview");
					System.out.println("show updated table.................");
					mac.showDetails();

				}
				if (status.equals("accepted")) {
					System.out.println("Enter 1 to confirm the candidate:");
					System.out.println("Enter 2 to reject the candidate:");
					int choice1 = sc.nextInt();
					if (choice1 == 1) {
						mac.confirmationStatus(idd, "confirmed");
						status = "confirmed";
						System.out.println("show updated table.................");
						mac.showDetails();

					}
					if (choice1 == 2) {
						mac.confirmationStatus(idd, "rejected");
						status = "rejected";
						System.out.println("show updated table.................");
						mac.showDetails();

					}
				}
				if (status.equals("confirmed")) {
					System.out.println("Status of the candidate can not be changed.");
					System.out.println("show updated table.................");
					mac.showDetails();

				}
				break;
			}
			}
			System.out.println("Do You Want To Continue");
			ch = sc.next();
		} while (ch.equals("y"));
	}
}
