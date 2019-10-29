package com.uas.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.uas.beans.Application;
import com.uas.beans.ProgramScheduled;
import com.uas.exception.StatusException;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.service.MACservice;
import com.uas.service.MACserviceImpl;
import com.uas.validation.ApplicantValidation;

public class ApplicantUI {
	
	public void apply() {
		
		Scanner sc = new Scanner(System.in);
		String input = null;
		
		Application app = new Application();
		
		ApplicationService appServ = new ApplicationServiceImpl();
		ApplicantValidation appVal=new  ApplicantValidation();
		
		MACservice mac = new MACserviceImpl();
	System.out.println("----List of all Programs-----");
	//appServ.showDetails();
	List<ProgramScheduled> list1=new ArrayList<ProgramScheduled>();
	list1=appServ.showScheduledDetails();
Iterator<ProgramScheduled> itr =list1.iterator();
while(itr.hasNext())
{
System.out.println(itr.next());

}
	int id_seq = app.getApp_id();
	System.out.println("enter Applicant's full name:");
	input = sc.next();
	while( !appVal.validateLetters(input))
	{
		System.out.println("enter valid Applicant's full name :");
		input = sc.next();
	}	
	app.setFull_name(input);
	System.out.println("enter date of birth in format DD/MM/YYYY");
	input = sc.next();
	while( !appVal.isValidDate(input))
	{
		System.out.println("enter valid Applicant's date of birth in format DD/MM/YYYY:");
		input = sc.next();
	}
	app.setDob(input);

	System.out.println("Choose Highest Qualification");
	System.out.println("1.B.tech");
	System.out.println("2.M.tech");
	System.out.println("select choice....");
	int c = sc.nextInt();
	switch (c) {
	case 1:
		app.setHighest_qualification("B.tech");
		System.out.println("Selected choice B.tech");
		break;
	case 2:
		app.setHighest_qualification("M.tech");
		System.out.println("Selected choice M.tech");
		break;
	default:
		System.out.println("invalid choice");
		break;
	}

	System.out.println(" Enter Marks obtained in Highest Qualification in percent:");
	double m = sc.nextDouble();
	while( !appVal.isValidPercent(m))
	{
		System.out.println("enter valid Applicant's Highest Qualification in percent:");
		m = sc.nextDouble();
	}
	app.setMarks(m);

	System.out.println("enter your goals");
	input = sc.next();
	app.setGoals(input);

	System.out.println("enter your email-id");
	input = sc.next();
	while( !appVal.isValidEmail(input))
	{
		System.out.println("enter valid email:");
		input = sc.next();
	}
	app.setEmail(input);

	//iNCREASE THE VARCHARVALUE IN SQL
	
	
	System.out.println("enter Scheduled_Program_id ");// later change it
	System.out.println("Choose the program id from the scheduled programs list:");
	//System.out.println(appServ.showScheduledDetails());
	List<ProgramScheduled> list2=new ArrayList<ProgramScheduled>();
	list2=appServ.showScheduledDetails();
	Iterator<ProgramScheduled> itr1 =list2.iterator();
	while(itr1.hasNext())
	{
		System.out.println(itr1.next());
		
	}
	input = sc.next();
	app.setSch_prog_id(input);
	
	System.out.println("current  status : applied");
	input = "applied";
	app.setStatus(input);
	
	System.out.println("date of interview....");
	input = "none";
	app.setDate_of_interview(input);
	int rows=appServ.addApplicationDetails(app);
	mac.showDetails();
	
	
	}
	public void checkStatus() {
		try
		{
		Scanner sc = new Scanner(System.in);
		ApplicationService appServ = new ApplicationServiceImpl();
		System.out.println("Enter your application Id");
		int id = sc.nextInt();
		String currentStatus = appServ.checkStatus(id);
		System.out.println("Your application status is '"+currentStatus+"'");
		}
		catch(StatusException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
