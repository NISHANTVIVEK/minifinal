package com.uas.service;

import java.util.List;

import com.uas.beans.Program;
import com.uas.dao.AdminDao;
import com.uas.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {
	AdminDao dao = new AdminDaoImpl();
	public void addDetails(Program prg)
	{
		dao.adddetails(prg);
	}
	public void deleteDetails(String p)
	{
		dao.deleteDetails(p);
	}
	@Override
	public void deleteScheduledDetails(String p) {
		dao.deleteScheduledDetails(p);
		
	}

}
