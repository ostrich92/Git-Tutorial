package com.project.OstrichCompany.basicInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.basicInfo.to.FinancialAccountAssociatesTO;

public interface FinancialAccountAssociatesDAO {

	public ArrayList<FinancialAccountAssociatesTO> selectFinancialAccountAssociatesListByCompany();

	public ArrayList<FinancialAccountAssociatesTO> selectFinancialAccountAssociatesListByWorkplace(
			String workplaceCode);

	public void insertFinancialAccountAssociates(FinancialAccountAssociatesTO bean);

	public void updateFinancialAccountAssociates(FinancialAccountAssociatesTO bean);

	public void deleteFinancialAccountAssociates(FinancialAccountAssociatesTO bean);

}
