package com.project.OstrichCompany.authorityManager.applicationService;

import java.util.ArrayList;

import com.project.OstrichCompany.authorityManager.exception.IdNotFoundException;
import com.project.OstrichCompany.authorityManager.exception.PwMissMatchException;
import com.project.OstrichCompany.authorityManager.exception.PwNotFoundException;
import com.project.OstrichCompany.hr.dao.EmpSearchingDAO;
import com.project.OstrichCompany.hr.dao.EmployeeSecretDAO;
import com.project.OstrichCompany.hr.to.EmpInfoTO;
import com.project.OstrichCompany.hr.to.EmployeeSecretTO;

public class LogInApplicationServiceImpl implements LogInApplicationService {

	private EmpSearchingDAO empSearchDAO;
	private EmployeeSecretDAO empSecretDAO;

	public void setEmpSearchDAO(EmpSearchingDAO empSearchDAO) {
		this.empSearchDAO = empSearchDAO;
	}

	public void setEmpSecretDAO(EmployeeSecretDAO empSecretDAO) {
		this.empSecretDAO = empSecretDAO;
	}

	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException {

		EmpInfoTO bean = checkEmpInfo(companyCode, workplaceCode, inputId);
		checkPassWord(companyCode, bean.getEmpCode(), inputPassWord);

		return bean;
	}

	private EmpInfoTO checkEmpInfo(String companyCode, String workplaceCode, String inputId)
			throws IdNotFoundException {

		EmpInfoTO bean = null;
		ArrayList<EmpInfoTO> empInfoBeanList = empSearchDAO.getTotalEmpInfo(companyCode, workplaceCode, inputId);

		if (empInfoBeanList.size() == 1) {

			for (EmpInfoTO e : empInfoBeanList) {
				bean = e;
			}

		} else if (empInfoBeanList.size() == 0) {
			throw new IdNotFoundException("입력된 정보에 해당하는 사원은 없습니다.");
		}

		return bean;
	}

	private void checkPassWord(String companyCode, String empCode, String inputPassWord)
			throws PwMissMatchException, PwNotFoundException {

		EmployeeSecretTO bean = empSecretDAO.selectUserPassWord(companyCode, empCode);

		StringBuffer userPassWord = new StringBuffer();

		if (bean != null) {
			userPassWord.append(bean.getUserPassword());

			// 회원ID 는 있으나 passWord Data 가 없는 경우
		} else if (bean == null || bean.getUserPassword().equals("") || bean.getUserPassword() == null) {
			throw new PwNotFoundException("비밀번호 정보를 찾을 수 없습니다.");
		}

		if (!inputPassWord.equals(userPassWord.toString())) {
			throw new PwMissMatchException("비밀번호가 가입된 정보와 같지 않습니다.");
		}

	}

}
