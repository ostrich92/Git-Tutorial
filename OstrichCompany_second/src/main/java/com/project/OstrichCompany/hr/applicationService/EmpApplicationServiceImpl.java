package com.project.OstrichCompany.hr.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.project.OstrichCompany.base.dao.CodeDetailDAO;
import com.project.OstrichCompany.base.to.CodeDetailTO;
import com.project.OstrichCompany.hr.dao.EmpSearchingDAO;
import com.project.OstrichCompany.hr.dao.EmployeeBasicDAO;
import com.project.OstrichCompany.hr.dao.EmployeeDetailDAO;
import com.project.OstrichCompany.hr.dao.EmployeeSecretDAO;
import com.project.OstrichCompany.hr.to.EmpInfoTO;
import com.project.OstrichCompany.hr.to.EmployeeBasicTO;
import com.project.OstrichCompany.hr.to.EmployeeDetailTO;
import com.project.OstrichCompany.hr.to.EmployeeSecretTO;

public class EmpApplicationServiceImpl implements EmpApplicationService {

	private EmployeeBasicDAO empBasicDAO;
	private EmployeeDetailDAO empDetailDAO;
	private EmployeeSecretDAO empSecretDAO;
	private EmpSearchingDAO empSearchDAO;
	private CodeDetailDAO codeDetailDAO;

	public void setEmpBasicDAO(EmployeeBasicDAO empBasicDAO) {
		this.empBasicDAO = empBasicDAO;
	}

	public void setEmpDetailDAO(EmployeeDetailDAO empDetailDAO) {
		this.empDetailDAO = empDetailDAO;
	}

	public void setEmpSecretDAO(EmployeeSecretDAO empSecretDAO) {
		this.empSecretDAO = empSecretDAO;
	}

	public void setEmpSearchDAO(EmpSearchingDAO empSearchDAO) {
		this.empSearchDAO = empSearchDAO;
	}

	public void setCodeDetailDAO(CodeDetailDAO codeDetailDAO) {
		this.codeDetailDAO = codeDetailDAO;
	}

	public ArrayList<EmpInfoTO> getAllEmpList(String searchCondition, String[] paramArray) {

		ArrayList<EmpInfoTO> empList = empSearchDAO.selectAllEmpList(searchCondition, paramArray);

		for (EmpInfoTO bean : empList) {

			bean.setEmpDetailTOList(empDetailDAO.selectEmployeeDetailList(bean.getCompanyCode(), bean.getEmpCode()));

			bean.setEmpSecretTOList(empSecretDAO.selectEmployeeSecretList(bean.getCompanyCode(), bean.getEmpCode()));

		}
		
		return empList;

	}

	public EmpInfoTO getEmpInfo(String companyCode, String empCode) {

		EmpInfoTO bean = new EmpInfoTO();

		ArrayList<EmployeeDetailTO> empDetailList = empDetailDAO.selectEmployeeDetailList(companyCode, empCode);

		ArrayList<EmployeeSecretTO> empSecretList = empSecretDAO.selectEmployeeSecretList(companyCode, empCode);

		bean.setEmpDetailTOList(empDetailList);
		bean.setEmpSecretTOList(empSecretList);

		EmployeeBasicTO basicBean = empBasicDAO.selectEmployeeBasicBean(companyCode, empCode);

		if (basicBean != null) {

			bean.setCompanyCode(companyCode);
			bean.setEmpCode(empCode);
			bean.setEmpName(basicBean.getEmpName());
			bean.setEmpEngName(basicBean.getEmpEngName());
			bean.setSocialSecurityNumber(basicBean.getSocialSecurityNumber());
			bean.setHireDate(basicBean.getHireDate());
			bean.setRetirementDate(basicBean.getRetirementDate());
			bean.setUserOrNot(basicBean.getUserOrNot());
			bean.setBirthDate(basicBean.getBirthDate());
			bean.setGender(basicBean.getGender());

		}

		return bean;

	}

	public String getNewEmpCode(String companyCode) {

		String newEmpCode = null;

		ArrayList<EmployeeBasicTO> empBasicList = empBasicDAO.selectEmployeeBasicList(companyCode);

		TreeSet<Integer> empCodeNoSet = new TreeSet<>();

		for (EmployeeBasicTO bean : empBasicList) {

			if (bean.getEmpCode().startsWith("EMP-")) {

				try {

					Integer no = Integer.parseInt(bean.getEmpCode().split("EMP-")[1]);
					empCodeNoSet.add(no);

				} catch (NumberFormatException e) {

					// "EMP-" 다음 부분을 Integer 로 변환하지 못하는 경우 : 그냥 다음 반복문 실행

				}

			}

		}

		if (empCodeNoSet.isEmpty()) {
			newEmpCode = "EMP-" + String.format("%03d", 1);
		} else {
			newEmpCode = "EMP-" + String.format("%03d", empCodeNoSet.pollLast() + 1);
		}

		return newEmpCode;
	}

	public HashMap<String, Object> batchEmpBasicListProcess(ArrayList<EmployeeBasicTO> empBasicList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();

		// 사원 정보는 추가만 가능 => 수정, 삭제가 없으므로 주석 처리함
		// ArrayList<String> updateList = new ArrayList<>();
		// ArrayList<String> deleteList = new ArrayList<>();

		CodeDetailTO detailCodeBean = new CodeDetailTO();

		for (EmployeeBasicTO bean : empBasicList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				empBasicDAO.insertEmployeeBasic(bean);

				insertList.add(bean.getEmpCode());

				// CODE_DETAIL 테이블에 Insert
				detailCodeBean.setDivisionCodeNo("HR-02");
				detailCodeBean.setDetailCode(bean.getEmpCode());
				detailCodeBean.setDetailCodeName(bean.getEmpEngName());

				codeDetailDAO.insertDetailCode(detailCodeBean);

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		// resultMap.put("UPDATE", updateList);
		// resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	public HashMap<String, Object> batchEmpDetailListProcess(ArrayList<EmployeeDetailTO> empDetailList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();

		// 사원 정보는 추가만 가능 => 수정, 삭제가 없으므로 주석 처리함
		// ArrayList<String> updateList = new ArrayList<>();
		// ArrayList<String> deleteList = new ArrayList<>();

		for (EmployeeDetailTO bean : empDetailList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				empDetailDAO.insertEmployeeDetail(bean);

				insertList.add(bean.getEmpCode());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		// resultMap.put("UPDATE", updateList);
		// resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	public HashMap<String, Object> batchEmpSecretListProcess(ArrayList<EmployeeSecretTO> empSecretList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();

		// 사원 정보는 추가만 가능 => 수정, 삭제가 없으므로 주석 처리함
		// ArrayList<String> updateList = new ArrayList<>();
		// ArrayList<String> deleteList = new ArrayList<>();

		for (EmployeeSecretTO bean : empSecretList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				empSecretDAO.insertEmployeeSecret(bean);

				insertList.add(bean.getEmpCode());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		// resultMap.put("UPDATE", updateList);
		// resultMap.put("DELETE", deleteList);

		return resultMap;

	}

	@Override
	public Boolean checkUserIdDuplication(String companyCode, String newUserId) {

		ArrayList<EmployeeDetailTO> empDetailList = null;

		Boolean duplicated = false;

		empDetailList = empDetailDAO.selectUserIdList(companyCode);

		System.out.println();

		for (EmployeeDetailTO bean : empDetailList) {

			if (bean.getUserId().equals(newUserId)) {

				duplicated = true;

			}

		}

		return duplicated; // 중복된 코드이면 true 반환
	}

	@Override
	public Boolean checkEmpCodeDuplication(String companyCode, String newEmpCode) {

		ArrayList<EmployeeBasicTO> empBasicList = null;

		Boolean duplicated = false;

		empBasicList = empBasicDAO.selectEmployeeBasicList(companyCode);

		for (EmployeeBasicTO bean : empBasicList) {

			if (bean.getEmpCode().equals(newEmpCode)) {

				duplicated = true;

			}

		}

		return duplicated; // 중복된 코드이면 true 반환
	}

}
