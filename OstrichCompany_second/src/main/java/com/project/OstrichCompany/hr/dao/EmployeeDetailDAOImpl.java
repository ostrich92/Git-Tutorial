package com.project.OstrichCompany.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.hr.to.EmployeeDetailTO;

public class EmployeeDetailDAOImpl implements EmployeeDetailDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public ArrayList<EmployeeDetailTO> selectEmployeeDetailList(String companyCode, String empCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmployeeDetailTO> employeeDetailList = new ArrayList<EmployeeDetailTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * SELECT * FROM EMPLOYEE_DETAIL WHERE COMPANY_CODE = ? AND EMP_CODE =?
			 */

			query.append("SELECT * FROM EMPLOYEE_DETAIL WHERE COMPANY_CODE = ?  AND EMP_CODE =?");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, companyCode);
			pstmt.setString(2, empCode);

			rs = pstmt.executeQuery();

			EmployeeDetailTO bean = null;

			while (rs.next()) {
				bean = new EmployeeDetailTO();

				bean.setCompanyCode(rs.getString("COMPANY_CODE"));
				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setSeq(rs.getInt("SEQ"));
				bean.setUpdateHistory(rs.getString("UPDATE_HISTORY"));
				bean.setUpdateDate(rs.getString("UPDATE_DATE"));
				bean.setWorkplaceCode(rs.getString("WORKPLACE_CODE"));
				bean.setDeptCode(rs.getString("DEPT_CODE"));
				bean.setPositionCode(rs.getString("POSITION_CODE"));
				bean.setUserId(rs.getString("USER_ID"));
				bean.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setZipCode(rs.getString("ZIP_CODE"));
				bean.setBasicAddress(rs.getString("BASIC_ADDRESS"));
				bean.setDetailAddress(rs.getString("DETAIL_ADDRESS"));
				bean.setLevelOfEducation(rs.getString("LEVEL_OF_EDUCATION"));
				bean.setImage(rs.getString("IMAGE"));

				employeeDetailList.add(bean);
			}

			return employeeDetailList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public ArrayList<EmployeeDetailTO> selectUserIdList(String companyCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<EmployeeDetailTO> employeeDetailList = new ArrayList<EmployeeDetailTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * 
			 * WITH DETAIL_INFO AS ( ( SELECT EMP_CODE, USER_ID, SEQ FROM EMPLOYEE_DETAIL
			 * WHERE COMPANY_CODE = ? ) ),
			 * 
			 * MAX_SEQ AS ( SELECT EMP_CODE, MAX(SEQ) AS SEQ FROM DETAIL_INFO GROUP BY
			 * EMP_CODE ),
			 * 
			 * ALL_USER_ID AS ( SELECT EMP_CODE, USER_ID FROM DETAIL_INFO WHERE ( EMP_CODE,
			 * SEQ ) IN ( SELECT EMP_CODE, SEQ FROM MAX_SEQ ) )
			 * 
			 * SELECT EMP_CODE, USER_ID FROM ALL_USER_ID WHERE USER_ID IS NOT NULL ORDER BY
			 * EMP_CODE
			 * 
			 */

			query.append("WITH DETAIL_INFO AS\r\n"
					+ "( ( SELECT EMP_CODE, USER_ID, SEQ FROM EMPLOYEE_DETAIL WHERE COMPANY_CODE = ? ) ),\r\n" + "\r\n"
					+ "MAX_SEQ AS\r\n" + "( SELECT EMP_CODE, MAX(SEQ) AS SEQ \r\n" + "FROM DETAIL_INFO\r\n"
					+ "GROUP BY EMP_CODE ),\r\n" + "\r\n" + "ALL_USER_ID AS\r\n" + "( SELECT EMP_CODE, USER_ID \r\n"
					+ "FROM DETAIL_INFO\r\n" + "WHERE ( EMP_CODE, SEQ ) IN ( SELECT EMP_CODE, SEQ FROM MAX_SEQ ) )\r\n"
					+ "\r\n" + "SELECT EMP_CODE, USER_ID FROM ALL_USER_ID\r\n" + "WHERE USER_ID IS NOT NULL\r\n"
					+ "ORDER BY EMP_CODE");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, companyCode);

			rs = pstmt.executeQuery();

			EmployeeDetailTO bean = null;

			while (rs.next()) {
				bean = new EmployeeDetailTO();

				bean.setEmpCode(rs.getString("EMP_CODE"));
				bean.setUserId(rs.getString("USER_ID"));

				employeeDetailList.add(bean);
			}

			return employeeDetailList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}

	}

	public void insertEmployeeDetail(EmployeeDetailTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * Insert into EMPLOYEE_DETAIL (COMPANY_CODE , EMP_CODE , SEQ , UPDATE_HISTORY ,
			 * UPDATE_DATE , WORKPLACE_CODE , DEPT_CODE , POSITION_CODE , USER_ID ,
			 * PHONE_NUMBER , EMAIL , ZIP_CODE , BASIC_ADDRESS , DETAIL_ADDRESS ,
			 * LEVEL_OF_EDUCATION,IMAGE) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?
			 * , ? , ? , ? , ? , ? )
			 */

			query.append("Insert into EMPLOYEE_DETAIL \r\n" + "(COMPANY_CODE , EMP_CODE , SEQ , \r\n"
					+ "UPDATE_HISTORY , UPDATE_DATE , WORKPLACE_CODE , \r\n"
					+ "DEPT_CODE , POSITION_CODE , USER_ID , \r\n" + "PHONE_NUMBER , EMAIL , ZIP_CODE , \r\n"
					+ "BASIC_ADDRESS , DETAIL_ADDRESS , \r\n" + "LEVEL_OF_EDUCATION,IMAGE) \r\n"
					+ "values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getCompanyCode());
			pstmt.setString(2, bean.getEmpCode());
			pstmt.setInt(3, bean.getSeq());
			pstmt.setString(4, bean.getUpdateHistory());
			pstmt.setString(5, bean.getUpdateDate());
			pstmt.setString(6, bean.getWorkplaceCode());
			pstmt.setString(7, bean.getDeptCode());
			pstmt.setString(8, bean.getPositionCode());
			pstmt.setString(9, bean.getUserId());
			pstmt.setString(10, bean.getPhoneNumber());
			pstmt.setString(11, bean.getEmail());
			pstmt.setString(12, bean.getZipCode());
			pstmt.setString(13, bean.getBasicAddress());
			pstmt.setString(14, bean.getDetailAddress());
			pstmt.setString(15, bean.getLevelOfEducation());
			pstmt.setString(16, bean.getImage());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}
