package com.project.OstrichCompany.hr.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.hr.to.EmployeeBasicTO;

public interface EmployeeBasicDAO {

	public ArrayList<EmployeeBasicTO> selectEmployeeBasicList(String companyCode);

	public EmployeeBasicTO selectEmployeeBasicBean(String companyCode, String empCode);

	public void insertEmployeeBasic(EmployeeBasicTO bean);
}
