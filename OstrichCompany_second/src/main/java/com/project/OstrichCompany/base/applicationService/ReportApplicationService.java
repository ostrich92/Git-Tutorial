package com.project.OstrichCompany.base.applicationService;

import java.util.ArrayList;

import com.project.OstrichCompany.base.to.ContractReportTO;
import com.project.OstrichCompany.base.to.EstimateReportTO;

public interface ReportApplicationService {

	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo);

	public ArrayList<ContractReportTO> getContractReport(String contractNo);
	
}
