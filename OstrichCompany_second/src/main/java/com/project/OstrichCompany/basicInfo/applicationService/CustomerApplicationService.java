package com.project.OstrichCompany.basicInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.OstrichCompany.basicInfo.to.CustomerTO;

public interface CustomerApplicationService {

	public ArrayList<CustomerTO> getCustomerList(String searchCondition, String companyCode, String workplaceCode);

	public String getNewCustomerCode(String workplaceCode);

	public HashMap<String, Object> batchCustomerListProcess(ArrayList<CustomerTO> customerList);

}
