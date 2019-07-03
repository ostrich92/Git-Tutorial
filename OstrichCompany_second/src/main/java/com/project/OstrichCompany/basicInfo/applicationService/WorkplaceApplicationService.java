package com.project.OstrichCompany.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.OstrichCompany.basicInfo.to.WorkplaceTO;

public interface WorkplaceApplicationService {

	public ArrayList<WorkplaceTO> getWorkplaceList(String companyCode);

	public String getNewWorkplaceCode(String companyCode);

	public HashMap<String, Object> batchWorkplaceListProcess(ArrayList<WorkplaceTO> workplaceList);

}
