package com.project.OstrichCompany.logistics.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;

public class DeliveryResultDAOImpl implements DeliveryResultDAO {

	// 참조변수 선언
	private DataSourceTransactionManager dataSourceTransactionManager;

	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	@Override
	public List<DeliveryResultTO> selectDeliveyResultList() {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<DeliveryResultTO> deliveryResultList = new ArrayList<>();

		try {

			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			  SELECT * FROM DELIVERY_RESULT
			 */
			query.append("SELECT * FROM DELIVERY_RESULT");

			pstmt = conn.prepareStatement(query.toString());

			rs = pstmt.executeQuery();

			DeliveryResultTO bean = null;

			while (rs.next()) {

				bean = new DeliveryResultTO();

				bean.setDeliveryNo(rs.getString("DELIVERY_NO"));
				bean.setContractDetailNo(rs.getString("CONTRACT_DETAIL_NO"));
				bean.setWarehouseCode(rs.getString("WAREHOUSE_CODE"));
				bean.setItemCode(rs.getString("ITEM_CODE"));
				bean.setItemName(rs.getString("ITEM_NAME"));
				bean.setUnitOfDeliveryResult(rs.getString("UNIT_OF_DELIVERY_RESULT"));
				bean.setDeliveryDate(rs.getString("DELIVERY_DATE"));
				bean.setDeliveryAmount(rs.getInt("DELIVERY_AMOUNT"));
				bean.setDescription(rs.getString("DESCRIPTION"));

				deliveryResultList.add(bean);

			}

			return deliveryResultList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void insertDeliveryResult(DeliveryResultTO bean) {

		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			/*
			 * Insert into Delivery_Result (DELIVERY_NO, CONTRACT_DETAIL_NO, WAREHOUSE_CODE,
			 * ITEM_CODE, ITEM_NAME, UNIT_OF_DELIVERY_RESULT, DELIVERY_DATE,
			 * DELIVERY_AMOUNT, DESCRIPTION) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
			 */
			query.append("Insert into Delivery_Result\r\n" + "(DELIVERY_NO, CONTRACT_DETAIL_NO, WAREHOUSE_CODE,\r\n"
					+ "ITEM_CODE, ITEM_NAME, UNIT_OF_DELIVERY_RESULT,\r\n"
					+ "DELIVERY_DATE, DELIVERY_AMOUNT, DESCRIPTION) \r\n" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getDeliveryNo());
			pstmt.setString(2, bean.getContractDetailNo());
			pstmt.setString(3, bean.getWarehouseCode());
			pstmt.setString(4, bean.getItemCode());
			pstmt.setString(5, bean.getItemName());
			pstmt.setString(6, bean.getUnitOfDeliveryResult());
			pstmt.setString(7, bean.getDeliveryDate());
			pstmt.setInt(8, bean.getDeliveryAmount());
			pstmt.setString(9, bean.getDescription());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}

	}

	public void updateDeliveryResult(DeliveryResultTO bean) {

	

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			/*
			 * UPDATE Delivery_Result SET CONTRACT_DETAIL_NO = ? , WAREHOUSE_CODE = ?,
			 * ITEM_CODE = ? , ITEM_NAME = ? , UNIT_OF_DELIVERY_RESULT = ?, DELIVERY_DATE =
			 * ? , DELIVERY_AMOUNT = ? , DESCRIPTION = ? WHERE DELIVERY_NO = ?
			 */
			query.append("UPDATE Delivery_Result SET \r\n" + "CONTRACT_DETAIL_NO = ? , WAREHOUSE_CODE = ?,\r\n"
					+ "ITEM_CODE = ? , ITEM_NAME = ? , UNIT_OF_DELIVERY_RESULT = ?,\r\n"
					+ "DELIVERY_DATE = ? , DELIVERY_AMOUNT = ? , DESCRIPTION = ?\r\n" + "WHERE DELIVERY_NO = ?");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getContractDetailNo());
			pstmt.setString(2, bean.getWarehouseCode());
			pstmt.setString(3, bean.getItemCode());
			pstmt.setString(4, bean.getItemName());
			pstmt.setString(5, bean.getUnitOfDeliveryResult());
			pstmt.setString(6, bean.getDeliveryDate());
			pstmt.setInt(7, bean.getDeliveryAmount());
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getDeliveryNo());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}

	}

	public void deleteDeliveryResult(DeliveryResultTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append("DELETE FROM DELIVERY_RESULT WHERE DELIVERY_NO = ?");
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getDeliveryNo());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}
