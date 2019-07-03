package com.project.OstrichCompany.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.hr.to.EmployeeSecretTO;

public class EmployeeSecretDAOImpl implements EmployeeSecretDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public ArrayList<EmployeeSecretTO> selectEmployeeSecretList(String companyCode, String empCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmployeeSecretTO> employeeSecretList = new ArrayList<EmployeeSecretTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * SELECT * FROM EMPLOYEE_SECRET WHERE COMPANY_CODE = ? AND EMP_CODE =?
			 */

			query.append("SELECT * FROM EMPLOYEE_SECRET WHERE COMPANY_CODE = ? AND EMP_CODE =?");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, companyCode);
			pstmt.setString(2, empCode);

			rs = pstmt.executeQuery();

			EmployeeSecretTO bean = null;

			while (rs.next()) {
				bean = new EmployeeSecretTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setSeq(rs.getInt("SEQ"));
				bean.setUserPassword(rs.getString("USER_PASSWORD"));

				employeeSecretList.add(bean);
			}

			return employeeSecretList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public EmployeeSecretTO selectUserPassWord(String companyCode, String empCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append("SELECT EMP_CODE, COMPANY_CODE, SEQ, USER_PASSWORD\r\n" + "		FROM EMPLOYEE_SECRET\r\n"
					+ "		WHERE ( EMP_CODE, COMPANY_CODE, SEQ) IN ( SELECT EMP_CODE, COMPANY_CODE, MAX(SEQ)\r\n"
					+ "		FROM EMPLOYEE_SECRET\r\n" + "		GROUP BY EMP_CODE, COMPANY_CODE )\r\n"
					+ "		AND COMPANY_CODE = ? AND EMP_CODE = ?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, companyCode);
			pstmt.setString(2, empCode);

			rs = pstmt.executeQuery();

			EmployeeSecretTO bean = null;
			while (rs.next()) {

				bean = new EmployeeSecretTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setSeq(rs.getInt("SEQ"));
				bean.setUserPassword(rs.getString("USER_PASSWORD"));
			}

			return bean;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void insertEmployeeSecret(EmployeeSecretTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * Insert into EMPLOYEE_SECRET ( COMPANY_CODE , EMP_CODE , SEQ, USER_PASSWORD )
			 * values ( ? , ? , ? , ? )
			 * 
			 */

			query.append("Insert into EMPLOYEE_SECRET " + "( COMPANY_CODE , EMP_CODE , SEQ, USER_PASSWORD ) "
					+ "values ( ? , ? , ? , ? )");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getCompanyCode());
			pstmt.setString(2, bean.getEmpCode());
			pstmt.setInt(3, bean.getSeq());
			pstmt.setString(4, bean.getUserPassword());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}
