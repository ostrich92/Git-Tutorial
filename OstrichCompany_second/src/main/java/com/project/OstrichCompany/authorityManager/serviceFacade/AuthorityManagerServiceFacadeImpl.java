package com.project.OstrichCompany.authorityManager.serviceFacade;

import javax.servlet.ServletContext;

import com.project.OstrichCompany.authorityManager.applicationService.LogInApplicationService;
import com.project.OstrichCompany.authorityManager.applicationService.UserMenuApplicationService;
import com.project.OstrichCompany.authorityManager.exception.IdNotFoundException;
import com.project.OstrichCompany.authorityManager.exception.PwMissMatchException;
import com.project.OstrichCompany.authorityManager.exception.PwNotFoundException;
import com.project.OstrichCompany.hr.to.EmpInfoTO;

public class AuthorityManagerServiceFacadeImpl implements AuthorityManagerServiceFacade {

	private LogInApplicationService logInAS;
	private UserMenuApplicationService userMenuAS;

	public void setLogInAS(LogInApplicationService logInAS) {
		this.logInAS = logInAS;
	}

	public void setUserMenuAS(UserMenuApplicationService userMenuAS) {
		this.userMenuAS = userMenuAS;
	}

	@Override
	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException {

		return logInAS.accessToAuthority(companyCode, workplaceCode, inputId, inputPassWord);

	}

	@Override
	public String getUserMenuCode(String workplaceCode, String deptCode, String positionCode,
			ServletContext application) {

		return userMenuAS.getUserMenuCode(workplaceCode, deptCode, positionCode, application);

	}

}
