package com.project.OstrichCompany.hr.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.hr.to.EmployeeSecretTO;

public interface EmployeeSecretDAO {

	public ArrayList<EmployeeSecretTO> selectEmployeeSecretList(String companyCode, String empCode);

	public EmployeeSecretTO selectUserPassWord(String companyCode, String empCode);

	public void insertEmployeeSecret(EmployeeSecretTO bean);
}
