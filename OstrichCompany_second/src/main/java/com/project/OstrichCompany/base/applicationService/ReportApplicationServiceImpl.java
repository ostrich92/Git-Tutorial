package com.project.OstrichCompany.base.applicationService;

import java.util.ArrayList;

import com.project.OstrichCompany.base.dao.ReportDAO;
import com.project.OstrichCompany.base.to.ContractReportTO;
import com.project.OstrichCompany.base.to.EstimateReportTO;

public class ReportApplicationServiceImpl implements ReportApplicationService {

	private ReportDAO reportDAO;

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public ArrayList<EstimateReportTO> getEstimateReport(String estimateNo) {

		ArrayList<EstimateReportTO> estimateList = reportDAO.selectEstimateReport(estimateNo);

		return estimateList;

	}

	@Override
	public ArrayList<ContractReportTO> getContractReport(String contractNo) {

		ArrayList<ContractReportTO> contractList = reportDAO.selectContractReport(contractNo);

		return contractList;
	}

}
