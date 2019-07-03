package com.project.OstrichCompany.base.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.base.to.ContractReportTO;
import com.project.OstrichCompany.base.to.EstimateReportTO;

public interface ReportDAO {

	public ArrayList<EstimateReportTO> selectEstimateReport(String estimateNo);

	public ArrayList<ContractReportTO> selectContractReport(String contractNo);
	
}
