package com.project.OstrichCompany.authorityManager.serviceFacade;

import javax.servlet.ServletContext;

import com.project.OstrichCompany.authorityManager.exception.IdNotFoundException;
import com.project.OstrichCompany.authorityManager.exception.PwMissMatchException;
import com.project.OstrichCompany.authorityManager.exception.PwNotFoundException;
import com.project.OstrichCompany.hr.to.EmpInfoTO;


public interface AuthorityManagerServiceFacade {

	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException;
	
	public String getUserMenuCode(String workplaceCode, String deptCode, String positionCode,
			ServletContext application);

}
