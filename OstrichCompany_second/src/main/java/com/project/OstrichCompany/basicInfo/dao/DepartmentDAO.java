package com.project.OstrichCompany.basicInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.basicInfo.to.DepartmentTO;

public interface DepartmentDAO {

	public ArrayList<DepartmentTO> selectDepartmentListByCompany(String companyCode);

	public ArrayList<DepartmentTO> selectDepartmentListByWorkplace(String workplaceCode);

	public void insertDepartment(DepartmentTO bean);

	public void updateDepartment(DepartmentTO bean);

	public void deleteDepartment(DepartmentTO bean);

}
