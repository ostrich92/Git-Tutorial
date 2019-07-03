package com.project.OstrichCompany.logistics.sales.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.logistics.production.to.ContractDetailInMpsAvailableTO;
import com.project.OstrichCompany.logistics.sales.to.ContractDetailTO;

public interface ContractDetailDAO {

	public ArrayList<ContractDetailTO> selectContractDetailList(String contractNo);

	public int selectContractDetailCount(String contractNo);

	public ArrayList<ContractDetailInMpsAvailableTO> selectContractDetailListInMpsAvailable(String searchCondition,
			String startDate, String endDate);

	public void insertContractDetail(ContractDetailTO TO);

	public void updateContractDetail(ContractDetailTO TO);

	public void changeMpsStatusOfContractDetail(String contractDetailNo, String mpsStatus);

	public void deleteContractDetail(ContractDetailTO TO);

}
