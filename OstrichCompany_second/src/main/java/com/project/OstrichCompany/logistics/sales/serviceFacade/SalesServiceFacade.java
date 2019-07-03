package com.project.OstrichCompany.logistics.sales.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.OstrichCompany.logistics.sales.to.ContractDetailTO;
import com.project.OstrichCompany.logistics.sales.to.ContractInfoTO;
import com.project.OstrichCompany.logistics.sales.to.ContractTO;
import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;
import com.project.OstrichCompany.logistics.sales.to.EstimateDetailTO;
import com.project.OstrichCompany.logistics.sales.to.EstimateTO;
import com.project.OstrichCompany.logistics.sales.to.SalesPlanTO;

public interface SalesServiceFacade {

	
	// EstimateApplicationServiceImpl
	public ArrayList<EstimateTO> getEstimateList(String dateSearchCondition, String startDate, String endDate);

	public ArrayList<EstimateDetailTO> getEstimateDetailList(String estimateNo);
	
	public HashMap<String, Object> addNewEstimate(String estimateDate, EstimateTO newEstimateTO);

	public HashMap<String, Object> batchEstimateDetailListProcess(ArrayList<EstimateDetailTO> estimateDetailTOList);	
	
	
	// ContractApplicationServiceImpl
	public ArrayList<ContractInfoTO> getContractList(String searchCondition, String[] paramArray);

	public ArrayList<ContractDetailTO> getContractDetailList(String estimateNo);
	
	public ArrayList<EstimateTO> getEstimateListInContractAvailable(String startDate, String endDate);

	public HashMap<String, Object> addNewContract(String contractDate, String personCodeInCharge, ContractTO workingContractTO, 
			EstimateTO previousEstimateTO);

	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailTO> contractDetailTOList);
	
	public void changeContractStatusInEstimate(String estimateNo , String contractStatus);
	
	
	// SalesPlanApplicationServiceImpl
	public ArrayList<SalesPlanTO> getSalesPlanList(String dateSearchCondition, String startDate, String endDate);
	
	public HashMap<String, Object> batchSalesPlanListProcess(ArrayList<SalesPlanTO> salesPlanTOList);

	
	// DeliveryApplicationServiceImpl
	public List<DeliveryResultTO> getDeliveyResultList();
	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryResultTO> deliveryTOList);

	
}
