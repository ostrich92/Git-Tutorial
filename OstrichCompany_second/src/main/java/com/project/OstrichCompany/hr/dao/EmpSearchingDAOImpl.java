package com.project.OstrichCompany.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.hr.to.EmpInfoTO;

public class EmpSearchingDAOImpl implements EmpSearchingDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public ArrayList<EmpInfoTO> selectAllEmpList(String searchCondition, String[] paramArray) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmpInfoTO> empList = new ArrayList<EmpInfoTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			 * WITH ALL_EMP_INFO AS ( SELECT E1.COMPANY_CODE, E1.EMP_CODE, E1.EMP_NAME,
			 * E1.EMP_ENG_NAME, E1.HIRE_DATE, E1.RETIREMENT_DATE, E1.USER_OR_NOT,
			 * E1.SOCIAL_SECURITY_NUMBER, E1.BIRTH_DATE, E1.GENDER, E2.SEQ,
			 * E2.UPDATE_HISTORY, E2.UPDATE_DATE, E2.USER_ID, E2.WORKPLACE_CODE,
			 * E2.DEPT_CODE, D.DEPT_NAME, E2.PHONE_NUMBER, E2.ZIP_CODE, E2.BASIC_ADDRESS,
			 * E2.DETAIL_ADDRESS, E2.LEVEL_OF_EDUCATION, E2.IMAGE, E2.EMAIL,
			 * E2.POSITION_CODE, P.POSITION_NAME FROM ( SELECT * FROM EMPLOYEE_BASIC WHERE
			 * COMPANY_CODE = ? ) E1, EMPLOYEE_DETAIL E2, DEPARTMENT D, POSITION P WHERE
			 * E1.COMPANY_CODE = E2.COMPANY_CODE (+) AND E1.EMP_CODE = E2.EMP_CODE (+) AND
			 * E2.WORKPLACE_CODE = D.WORKPLACE_CODE (+) AND E2.DEPT_CODE = D.DEPT_CODE (+)
			 * AND E2.WORKPLACE_CODE = P.WORKPLACE_CODE (+) AND E2.DEPT_CODE = P.DEPT_CODE
			 * (+) AND E2.POSITION_CODE = P.POSITION_CODE (+) ORDER BY E1.COMPANY_CODE,
			 * E1.EMP_CODE, P.POSITION_CODE ) ,
			 * 
			 * MAX_SEQ AS ( SELECT COMPANY_CODE, EMP_CODE, MAX(SEQ) AS SEQ FROM
			 * EMPLOYEE_DETAIL GROUP BY COMPANY_CODE, EMP_CODE )
			 * 
			 * SELECT COMPANY_CODE, EMP_CODE, USER_ID, EMP_NAME, DEPT_NAME, POSITION_NAME,
			 * USER_OR_NOT, IMAGE FROM ALL_EMP_INFO WHERE ( COMPANY_CODE, EMP_CODE, SEQ ) IN
			 * ( SELECT COMPANY_CODE, EMP_CODE, SEQ FROM MAX_SEQ ) OR USER_OR_NOT = 'N'
			 */
			query.append("WITH ALL_EMP_INFO AS\r\n"
					+ "( SELECT E1.COMPANY_CODE, E1.EMP_CODE, E1.EMP_NAME, E1.EMP_ENG_NAME, E1.HIRE_DATE, E1.RETIREMENT_DATE,\r\n"
					+ "    E1.USER_OR_NOT, E1.SOCIAL_SECURITY_NUMBER, E1.BIRTH_DATE, E1.GENDER, \r\n"
					+ "    E2.SEQ, E2.UPDATE_HISTORY, E2.UPDATE_DATE, E2.USER_ID, E2.WORKPLACE_CODE,\r\n"
					+ "    E2.DEPT_CODE, D.DEPT_NAME, E2.PHONE_NUMBER, E2.ZIP_CODE, E2.BASIC_ADDRESS, E2.DETAIL_ADDRESS, \r\n"
					+ "    E2.LEVEL_OF_EDUCATION, E2.IMAGE, E2.EMAIL, E2.POSITION_CODE, P.POSITION_NAME\r\n"
					+ "FROM \r\n" + "( SELECT * FROM EMPLOYEE_BASIC WHERE COMPANY_CODE = ? ) E1, \r\n"
					+ "EMPLOYEE_DETAIL E2, DEPARTMENT D, POSITION P\r\n"
					+ "WHERE E1.COMPANY_CODE = E2.COMPANY_CODE (+) AND E1.EMP_CODE = E2.EMP_CODE (+) \r\n"
					+ "AND E2.WORKPLACE_CODE = D.WORKPLACE_CODE (+) AND E2.DEPT_CODE = D.DEPT_CODE (+) \r\n"
					+ "AND E2.WORKPLACE_CODE = P.WORKPLACE_CODE (+) AND E2.DEPT_CODE = P.DEPT_CODE (+) \r\n"
					+ "AND E2.POSITION_CODE = P.POSITION_CODE (+)\r\n"
					+ "ORDER BY E1.COMPANY_CODE, E1.EMP_CODE, P.POSITION_CODE ) ,\r\n" + "\r\n" + "MAX_SEQ AS\r\n"
					+ "( SELECT COMPANY_CODE, EMP_CODE, MAX(SEQ) AS SEQ \r\n"
					+ "FROM EMPLOYEE_DETAIL GROUP BY COMPANY_CODE, EMP_CODE )\r\n" + "\r\n"
					+ "SELECT COMPANY_CODE, EMP_CODE, USER_OR_NOT, USER_ID, EMP_NAME, DEPT_NAME, POSITION_NAME, USER_OR_NOT, IMAGE\r\n"
					+ "FROM ALL_EMP_INFO\r\n");

			switch (searchCondition) {

			case "ALL":

				query.append("WHERE ( COMPANY_CODE, EMP_CODE, SEQ ) IN \r\n"
						+ "( SELECT COMPANY_CODE, EMP_CODE, SEQ FROM MAX_SEQ ) "); // 마지막 한칸 띄우기!!
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);

				break;

			case "WORKPLACE":

				query.append("WHERE ( COMPANY_CODE, EMP_CODE, SEQ ) IN \r\n"
						+ "( SELECT COMPANY_CODE, EMP_CODE, SEQ FROM MAX_SEQ ) "); // 마지막 한칸 띄우기!!
				query.append("AND WORKPLACE_CODE = ?");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);
				pstmt.setString(2, paramArray[1]);

				break;

			case "DEPT":
				query.append("WHERE ( COMPANY_CODE, EMP_CODE, SEQ ) IN \r\n"
						+ "( SELECT COMPANY_CODE, EMP_CODE, SEQ FROM MAX_SEQ ) "); // 마지막 한칸 띄우기!!
				query.append("AND DEPT_CODE = ? ");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);
				pstmt.setString(2, paramArray[1]);

				break;

			case "RETIREMENT":

				query.append("WHERE USER_OR_NOT = 'N' AND USER_ID IS NULL");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);

				break;

			}

			rs = pstmt.executeQuery();

			EmpInfoTO bean = null;

			while (rs.next()) {
				bean = new EmpInfoTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setUserOrNot(rs.getString("USER_OR_NOT"));
				bean.setUserId(rs.getString("USER_ID"));
				bean.setEmpName(rs.getString("EMP_NAME"));
				bean.setDeptName(rs.getString("DEPT_NAME"));
				bean.setPositionName(rs.getString("POSITION_NAME"));
				bean.setUserOrNot(rs.getString("USER_OR_NOT"));
				bean.setImage(rs.getString("IMAGE"));

				empList.add(bean);
			}

			return empList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public ArrayList<EmpInfoTO> getTotalEmpInfo(String companyCode, String workplaceCode, String userId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmpInfoTO> empInfoBeanList = new ArrayList<EmpInfoTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append("SELECT EMP_CODE, COMPANY_CODE, EMP_NAME, EMP_ENG_NAME, HIRE_DATE, RETIREMENT_DATE, \r\n"
					+ "	USER_OR_NOT, SOCIAL_SECURITY_NUMBER, BIRTH_DATE, GENDER, SEQ, UPDATE_HISTORY, UPDATE_DATE, \r\n"
					+ "	USER_ID, T1.WORKPLACE_CODE, WORKPLACE_NAME, DEPT_CODE, PHONE_NUMBER, EMAIL, ZIP_CODE, \r\n"
					+ "	BASIC_ADDRESS, DETAIL_ADDRESS, LEVEL_OF_EDUCATION, IMAGE, POSITION_CODE, \r\n"
					+ "	POSITION_NAME, DEPT_NAME\r\n" + "FROM \r\n"
					+ "( SELECT E1.EMP_CODE, E1.COMPANY_CODE, E1.EMP_NAME, E1.EMP_ENG_NAME, E1.HIRE_DATE, E1.RETIREMENT_DATE, \r\n"
					+ "		E1.USER_OR_NOT, E1.SOCIAL_SECURITY_NUMBER, E1.BIRTH_DATE, E1.GENDER, \r\n"
					+ "		E2.SEQ, E2.UPDATE_HISTORY, E2.UPDATE_DATE, E2.USER_ID, E2.WORKPLACE_CODE, W.WORKPLACE_NAME,\r\n"
					+ "		E2.DEPT_CODE, E2.PHONE_NUMBER, E2.ZIP_CODE, E2.BASIC_ADDRESS, E2.DETAIL_ADDRESS, \r\n"
					+ "		E2.LEVEL_OF_EDUCATION, E2.IMAGE, E2.POSITION_CODE, E2.EMAIL,\r\n"
					+ "		P.POSITION_NAME, D.DEPT_NAME\r\n"
					+ "	FROM EMPLOYEE_BASIC E1, EMPLOYEE_DETAIL E2, WORKPLACE W, POSITION P, DEPARTMENT D\r\n"
					+ "    WHERE E1.EMP_CODE = E2.EMP_CODE AND E1.COMPANY_CODE = W.COMPANY_CODE AND E2.WORKPLACE_CODE = W.WORKPLACE_CODE\r\n"
					+ "		AND E2.WORKPLACE_CODE = P.WORKPLACE_CODE AND E2.DEPT_CODE = P.DEPT_CODE AND E2.POSITION_CODE = P.POSITION_CODE\r\n"
					+ "    	AND E2.WORKPLACE_CODE = D.WORKPLACE_CODE AND E2.DEPT_CODE = D.DEPT_CODE\r\n"
					+ "    	AND ( E2.EMP_CODE, E2.SEQ ) IN ( SELECT EMP_CODE, MAX(SEQ) FROM EMPLOYEE_DETAIL GROUP BY EMP_CODE ) ) T1\r\n"
					+ "	WHERE T1.COMPANY_CODE = ? AND T1.WORKPLACE_CODE = ? AND T1.USER_ID = ?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, companyCode);
			pstmt.setString(2, workplaceCode);
			pstmt.setString(3, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				EmpInfoTO bean = new EmpInfoTO();

				bean.setSocialSecurityNumber(rs.getString("SOCIAL_SECURITY_NUMBER"));
				bean.setEmpEngName(rs.getString("EMP_ENG_NAME"));
				bean.setDeptName(rs.getString("DEPT_NAME"));
				bean.setUpdateHistory(rs.getString("UPDATE_HISTORY"));
				bean.setUpdateDate(rs.getString("UPDATE_DATE"));
				bean.setPositionCode(rs.getString("POSITION_CODE"));
				bean.setUserId(rs.getString("USER_ID"));
				bean.setLevelOfEducation(rs.getString("LEVEL_OF_EDUCATION"));
				bean.setUserOrNot(rs.getString("USER_OR_NOT"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setEmpName(rs.getString("EMP_NAME"));
				bean.setImage(rs.getString("IMAGE"));
				bean.setHireDate(rs.getString("HIRE_DATE"));
				bean.setRetirementDate(rs.getString("RETIREMENT_DATE"));
				bean.setWorkplaceCode(rs.getString("WORKPLACE_CODE"));
				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setBirthDate(rs.getString("BIRTH_DATE"));
				bean.setGender(rs.getString("GENDER"));
				bean.setDeptCode(rs.getString("DEPT_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setPositionName(rs.getString("POSITION_NAME"));
				bean.setWorkplaceName(rs.getString("WORKPLACE_NAME"));
				bean.setZipCode(rs.getString("ZIP_CODE"));
				bean.setBasicAddress(rs.getString("BASIC_ADDRESS"));
				bean.setDetailAddress(rs.getString("DETAIL_ADDRESS"));
				bean.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				bean.setSeq(rs.getInt("SEQ"));
				;

				empInfoBeanList.add(bean);
			}

			return empInfoBeanList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}
