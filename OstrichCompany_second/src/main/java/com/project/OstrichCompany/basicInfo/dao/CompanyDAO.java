package com.project.OstrichCompany.basicInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.basicInfo.to.CompanyTO;

public interface CompanyDAO {
	
	public ArrayList<CompanyTO> selectCompanyList();
	
	public void insertCompany(CompanyTO bean);
	
	public void updateCompany(CompanyTO bean);

	public void deleteCompany(CompanyTO bean);
	
}
