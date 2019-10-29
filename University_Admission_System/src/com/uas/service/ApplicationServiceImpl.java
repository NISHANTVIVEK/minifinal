package com.uas.service;



import java.util.List;

import com.uas.beans.Application;
import com.uas.beans.Program;
import com.uas.beans.ProgramScheduled;
import com.uas.dao.ApplicationDAO;
import com.uas.dao.ApplicationDAOImpl;

public class ApplicationServiceImpl implements ApplicationService {
	
   ApplicationDAO appDetails=null;

	 public ApplicationServiceImpl() {
		appDetails=new ApplicationDAOImpl();
	}
	
	@Override
	public int addApplicationDetails(Application app) {
	
		return appDetails.addApplicationDetails(app);
	}
	
	

	@Override
	public List<ProgramScheduled> showScheduledDetails() {
		return appDetails.showScheduledDetails();
	}

	@Override
	public List<Program> showDetails() {
		return appDetails.showDetails();
	}

	@Override
	public String checkStatus(int id) {
		return appDetails.checkStatus(id);
	}
	
	



}
