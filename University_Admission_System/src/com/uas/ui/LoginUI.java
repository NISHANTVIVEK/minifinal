package com.uas.ui;

import java.util.Scanner;

import com.uas.beans.Login;
import com.uas.exception.LoginException;
import com.uas.service.LoginService;
import com.uas.service.LoginServiceImpl;

import oracle.net.aso.e;

public class LoginUI {
	 void getLogin()
		{
		String user,pass,role;
		
		

		MacUI mac = new MacUI();
		AdminUI admin = new AdminUI();
		
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter valid username");
		user =sc.next();
		System.out.println("Enter valid password");
		pass=sc.next();
		
		
		Login l =new Login(user,pass);
		
		LoginService obj = new LoginServiceImpl();
		try
		{
		role =obj.authenticate(user,pass);
		if(role.equals("MAC"))
		{
			
			System.out.println("Welcome MAC");
			mac.getMacUI();

		}
		
		
		if(role.equals("ADMIN"))
		{
			System.out.println("Welcome ADMIN");
			admin.getAdminUI();

		
		}
		if(role.equals("null"))
		{
			System.out.println("--------");
		}
		}
		catch(LoginException e)
		{
			System.err.println(e.getMessage());
		}
	
		
		
		
	}
}


