package com.project.OstrichCompany.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.hr.to.EmployeeBasicTO;

public class EmployeeBasicDAOImpl implements EmployeeBasicDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public ArrayList<EmployeeBasicTO> selectEmployeeBasicList(String companyCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmployeeBasicTO> employeeBasicList = new ArrayList<EmployeeBasicTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * SELECT * FROM EMPLOYEE_BASIC WHERE COMPANY_CODE = ?
			 */

			query.append("SELECT * FROM EMPLOYEE_BASIC WHERE COMPANY_CODE = ? ");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, companyCode);

			rs = pstmt.executeQuery();

			EmployeeBasicTO bean = null;

			while (rs.next()) {
				bean = new EmployeeBasicTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setEmpEngName(rs.getString("EMP_NAME"));
				bean.setEmpEngName(rs.getString("EMP_ENG_NAME"));
				bean.setSocialSecurityNumber(rs.getString("SOCIAL_SECURITY_NUMBER"));
				bean.setHireDate(rs.getString("HIRE_DATE"));
				bean.setRetirementDate(rs.getString("RETIREMENT_DATE"));
				bean.setUserOrNot(rs.getString("USER_OR_NOT"));
				bean.setBirthDate(rs.getString("BIRTH_DATE"));
				bean.setGender(rs.getString("GENDER"));

				employeeBasicList.add(bean);
			}

			return employeeBasicList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public EmployeeBasicTO selectEmployeeBasicBean(String companyCode, String empCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * SELECT * FROM EMPLOYEE_BASIC WHERE COMPANY_CODE = ? AND EMP_CODE =?
			 */

			query.append("SELECT * FROM EMPLOYEE_BASIC WHERE COMPANY_CODE = ?  AND EMP_CODE =?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, companyCode);
			pstmt.setString(2, empCode);

			rs = pstmt.executeQuery();

			EmployeeBasicTO bean = null;

			while (rs.next()) {
				bean = new EmployeeBasicTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setEmpName(rs.getString("EMP_NAME"));
				bean.setEmpEngName(rs.getString("EMP_ENG_NAME"));
				bean.setSocialSecurityNumber(rs.getString("SOCIAL_SECURITY_NUMBER"));
				bean.setHireDate(rs.getString("HIRE_DATE"));
				bean.setRetirementDate(rs.getString("RETIREMENT_DATE"));
				bean.setUserOrNot(rs.getString("USER_OR_NOT"));
				bean.setBirthDate(rs.getString("BIRTH_DATE"));
				bean.setGender(rs.getString("GENDER"));

			}

			return bean;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void insertEmployeeBasic(EmployeeBasicTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * Insert into EMPLOYEE_BASIC ( COMPANY_CODE , EMP_CODE , EMP_NAME ,
			 * EMP_ENG_NAME , SOCIAL_SECURITY_NUMBER , HIRE_DATE , RETIREMENT_DATE ,
			 * USER_OR_NOT , BIRTH_DATE , GENDER ) values ( ? , ? , ? , ? , ? , ? , ? , ? ,
			 * ? , ? )
			 * 
			 */

			query.append("Insert into EMPLOYEE_BASIC \r\n"
					+ "( COMPANY_CODE , EMP_CODE , EMP_NAME , EMP_ENG_NAME , \r\n"
					+ "SOCIAL_SECURITY_NUMBER , HIRE_DATE , RETIREMENT_DATE , \r\n"
					+ "USER_OR_NOT , BIRTH_DATE , GENDER ) \r\n" + "values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getCompanyCode());
			pstmt.setString(2, bean.getEmpCode());
			pstmt.setString(3, bean.getEmpName());
			pstmt.setString(4, bean.getEmpEngName());
			pstmt.setString(5, bean.getSocialSecurityNumber());
			pstmt.setString(6, bean.getHireDate());
			pstmt.setString(7, bean.getRetirementDate());
			pstmt.setString(8, bean.getUserOrNot());
			pstmt.setString(9, bean.getBirthDate());
			pstmt.setString(10, bean.getGender());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}
