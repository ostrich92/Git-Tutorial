package com.project.OstrichCompany.basicInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.basicInfo.to.CustomerTO;

public interface CustomerDAO {

	public ArrayList<CustomerTO> selectCustomerListByCompany();

	public ArrayList<CustomerTO> selectCustomerListByWorkplace(String workplaceCode);
	
	public void insertCustomer(CustomerTO bean);

	public void updateCustomer(CustomerTO bean);

	public void deleteCustomer(CustomerTO bean);

}
