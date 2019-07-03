package com.project.OstrichCompany.basicInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.basicInfo.to.WorkplaceTO;

public interface WorkplaceDAO {

	public ArrayList<WorkplaceTO> selectWorkplaceList(String companyCode);

	public void insertWorkplace(WorkplaceTO bean);

	public void updateWorkplace(WorkplaceTO bean);

	public void deleteWorkplace(WorkplaceTO bean);
}
