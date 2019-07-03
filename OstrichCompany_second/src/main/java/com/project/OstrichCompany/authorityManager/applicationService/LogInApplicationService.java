package com.project.OstrichCompany.authorityManager.applicationService;

import com.project.OstrichCompany.authorityManager.exception.IdNotFoundException;
import com.project.OstrichCompany.authorityManager.exception.PwMissMatchException;
import com.project.OstrichCompany.authorityManager.exception.PwNotFoundException;
import com.project.OstrichCompany.hr.to.EmpInfoTO;

public interface LogInApplicationService {

	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException;

}
