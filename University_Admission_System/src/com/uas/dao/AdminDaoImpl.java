package com.uas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.service.ApplicationService;
import com.uas.service.ApplicationServiceImpl;
import com.uas.util.ConnectionFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDao {
	ApplicationService appServ = new ApplicationServiceImpl();
	ConnectionFactory conFactory = ConnectionFactory.getInstance();

	public void adddetails(Program prg) {
		System.out.println("Press 1 to offer new program");
		System.out.println("Press 2 to schedule offered program");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("--------New Program Form---------\n");
			System.out.println("Enter program name: ");
			prg.setProgram(sc.next());
			System.out.println("Enter program description: ");
			prg.setDescription(sc.next());		//BufferedReade)r
			System.out.println("Enter candidate eligibility: ");
			prg.setEligibility(sc.next());
			System.out.println("Enter program duration: ");
			prg.setDuration(sc.nextInt());
			System.out.println("Enter degree type: ");
			prg.setDegree(sc.next());
			
			String queryString = "INSERT INTO PROGRAMS_OFFERED( ProgramName,description,applicant_eligibility, duration , degree_certificate_offered )VALUES(?,?,?,?,?) ";

			try (Connection conn = conFactory.getConnection();
					PreparedStatement ptmt = conn.prepareStatement(queryString);) {
				ptmt.setString(1, prg.getProgram());
				ptmt.setString(2, prg.getDescription());
				ptmt.setString(3, prg.getEligibility());
				ptmt.setInt(4, prg.getDuration());
				ptmt.setString(5, prg.getDegree());
				int rows = ptmt.executeUpdate();
				System.out.println(rows + "Program INSERTED successfully....");

			} catch (SQLException e) {
				System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
			}
			System.out.println("Do you want to schedule this program");
			System.out.println("Press y to continue");
			String schedule = null;
			schedule = sc.next();	
			if (schedule.equals("y")) {
				addDetailsScheduled(prg.getProgram());
			}

		}
		if (choice == 2) {
			System.out.println("List of offered Programs available \n");

			List<Program> list1 = new ArrayList<Program>();
			list1 = appServ.showDetails();
			Iterator<Program> itr = list1.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());

			}
			System.out.println("Enter program Name");
			String programName = sc.next();
			addDetailsScheduled(programName);
		}

	}

	public void addDetailsScheduled(String programName) {

		String queryString = "INSERT INTO PROGRAMS_SCHEDULED( Scheduled_program_id,ProgramName,Location , start_date , end_date, sessions_per_week )VALUES(?,?,?,?,?,?) ";
		Scanner sc = new Scanner(System.in);
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			System.out.println("Enter scheduled program id:");
			ptmt.setString(1, sc.next());
			ptmt.setString(2, programName);
			System.out.println("Enter program location:");
			ptmt.setString(3, sc.next());
			System.out.println("Enter scheduled program's start date:");
			ptmt.setString(4, sc.next());
			System.out.println("Enter scheduled program's end date:");
			ptmt.setString(5, sc.next());
			System.out.println("Enter number of sessions per week:");
			ptmt.setInt(6, sc.nextInt());
			int rows = ptmt.executeUpdate();
			System.out.println(rows + "Program SCHEDULED successfully....");

		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}

	}

	public void deleteDetails(String p) {
		String queryString = "DELETE FROM PROGRAMS_OFFERED WHERE ProgramName=?";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			ptmt.setString(1, p);
			System.out.println(p);
			int rows = ptmt.executeUpdate();
			System.out.println(rows + "Program DELETED successfully....");
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}
	}
	public void deleteScheduledDetails(String p) {
		String queryString = "DELETE FROM PROGRAMS_SCHEDULED WHERE ProgramName=?";
		try (Connection conn = conFactory.getConnection();
				PreparedStatement ptmt = conn.prepareStatement(queryString);) {
			ptmt.setString(1, p);
			System.out.println("Deleting "+p);
			int rows = ptmt.executeUpdate();
			System.out.println("Scheduled Program DELETED successfully....");
		} catch (SQLException e) {
			System.err.format("SQL State:%s\n%s", e.getSQLState(), e.getMessage());
		}
	}

}
