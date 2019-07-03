package com.project.OstrichCompany.logistics.logisticsInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.common.transaction.DataSourceTransactionManager;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemInfoTO;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemTO;

public class ItemDAOImpl implements ItemDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public ArrayList<ItemInfoTO> selectAllItemList() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ItemInfoTO> itemInfoList = new ArrayList<ItemInfoTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			 * SELECT I.ITEM_CODE, I.ITEM_NAME, I.ITEM_GROUP_CODE, I.ITEM_CLASSIFICATION,
			 * I.UNIT_OF_STOCK, I.LOSS_RATE, I.LEAD_TIME, I.STANDARD_UNIT_PRICE,
			 * I.DESCRIPTION, C.CODE_USE_CHECK FROM ITEM I, CODE_DETAIL C WHERE I.ITEM_CODE
			 * = C.DETAIL_CODE (+)
			 */
			query.append("SELECT I.ITEM_CODE, I.ITEM_NAME, I.ITEM_GROUP_CODE, I.ITEM_CLASSIFICATION, \r\n"
					+ "    I.UNIT_OF_STOCK, I.LOSS_RATE, I.LEAD_TIME, I.STANDARD_UNIT_PRICE, I.DESCRIPTION, \r\n"
					+ "    C.CODE_USE_CHECK\r\n" + "FROM ITEM I, CODE_DETAIL C\r\n"
					+ "WHERE I.ITEM_CODE = C.DETAIL_CODE (+)");

			pstmt = conn.prepareStatement(query.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ItemInfoTO bean = new ItemInfoTO();

				bean.setItemCode(rs.getString("ITEM_CODE"));
				bean.setItemName(rs.getString("ITEM_NAME"));
				bean.setItemGroupCode(rs.getString("ITEM_GROUP_CODE"));
				bean.setItemClassification(rs.getString("ITEM_CLASSIFICATION"));
				bean.setUnitOfStock(rs.getString("UNIT_OF_STOCK"));
				bean.setLossRate(rs.getString("LOSS_RATE"));
				bean.setLeadTime(rs.getString("LEAD_TIME"));
				bean.setStandardUnitPrice(rs.getInt("STANDARD_UNIT_PRICE"));
				bean.setCodeUseCheck(rs.getString("CODE_USE_CHECK"));
				bean.setDescription(rs.getString("DESCRIPTION"));

				itemInfoList.add(bean);
			}

			return itemInfoList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public ArrayList<ItemInfoTO> selectItemList(String searchCondition, String paramArray[]) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ItemInfoTO> itemInfoList = new ArrayList<ItemInfoTO>();

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();

			/*
			 * WITH ITEM_WITH_CODE_USE_CHECK AS ( SELECT I.ITEM_CODE, I.ITEM_NAME,
			 * I.ITEM_GROUP_CODE, I.ITEM_CLASSIFICATION, I.UNIT_OF_STOCK, I.LOSS_RATE,
			 * I.LEAD_TIME, I.STANDARD_UNIT_PRICE, I.DESCRIPTION, C.CODE_USE_CHECK FROM ITEM
			 * I, CODE_DETAIL C WHERE I.ITEM_CODE = C.DETAIL_CODE (+) )
			 * 
			 * SELECT * FROM ITEM_WITH_CODE_USE_CHECK
			 */

			query.append("WITH ITEM_WITH_CODE_USE_CHECK AS\r\n"
					+ "( SELECT I.ITEM_CODE, I.ITEM_NAME, I.ITEM_GROUP_CODE, I.ITEM_CLASSIFICATION, \r\n"
					+ "    I.UNIT_OF_STOCK, I.LOSS_RATE, I.LEAD_TIME, I.STANDARD_UNIT_PRICE, I.DESCRIPTION, \r\n"
					+ "    C.CODE_USE_CHECK\r\n" + "FROM ITEM I, CODE_DETAIL C\r\n"
					+ "WHERE I.ITEM_CODE = C.DETAIL_CODE (+) )\r\n" + "\r\n"
					+ "SELECT * FROM ITEM_WITH_CODE_USE_CHECK ");

			switch (searchCondition) {

			case "ITEM_CLASSIFICATION":

				query.append("WHERE ITEM_CLASSIFICATION = ?");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);

				break;

			case "ITEM_GROUP_CODE":

				query.append("WHERE ITEM_GROUP_CODE = ?");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);

				break;

			case "STANDARD_UNIT_PRICE":

				query.append("WHERE STANDARD_UNIT_PRICE BETWEEN TO_NUMBER( ? ) AND TO_NUMBER ( ? )");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, paramArray[0]);
				pstmt.setString(2, paramArray[1]);

				break;

			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ItemInfoTO bean = new ItemInfoTO();

				bean.setItemCode(rs.getString("ITEM_CODE"));
				bean.setItemName(rs.getString("ITEM_NAME"));
				bean.setItemGroupCode(rs.getString("ITEM_GROUP_CODE"));
				bean.setItemClassification(rs.getString("ITEM_CLASSIFICATION"));
				bean.setUnitOfStock(rs.getString("UNIT_OF_STOCK"));
				bean.setLossRate(rs.getString("LOSS_RATE"));
				bean.setLeadTime(rs.getString("LEAD_TIME"));
				bean.setStandardUnitPrice(rs.getInt("STANDARD_UNIT_PRICE"));
				bean.setCodeUseCheck(rs.getString("CODE_USE_CHECK"));
				bean.setDescription(rs.getString("DESCRIPTION"));

				itemInfoList.add(bean);
			}

			return itemInfoList;

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void insertItem(ItemTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			 * Insert into ITEM
			 * (ITEM_CODE,ITEM_NAME,ITEM_GROUP_CODE,ITEM_CLASSIFICATION,UNIT_OF_STOCK,
			 * LOSS_RATE,LEAD_TIME,STANDARD_UNIT_PRICE,DESCRIPTION) values
			 * ('1111','1111','1111','IT-CI',null,null,null,null,null)
			 */
			query.append("Insert into ITEM \r\n" + "( ITEM_CODE, ITEM_NAME, ITEM_GROUP_CODE, "
					+ "ITEM_CLASSIFICATION, UNIT_OF_STOCK, LOSS_RATE, "
					+ "LEAD_TIME, STANDARD_UNIT_PRICE, DESCRIPTION) \r\n"
					+ "values ( ? , ? , ? , ? , ? , ? , ? , ? , ? )");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getItemCode());
			pstmt.setString(2, bean.getItemName());
			pstmt.setString(3, bean.getItemGroupCode());
			pstmt.setString(4, bean.getItemClassification());
			pstmt.setString(5, bean.getUnitOfStock());
			pstmt.setString(6, bean.getLossRate());
			pstmt.setString(7, bean.getLeadTime());
			pstmt.setInt(8, bean.getStandardUnitPrice());
			pstmt.setString(9, bean.getDescription());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void updateItem(ItemTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			 * UPDATE ITEM SET ITEM_NAME = ? , ITEM_GROUP_CODE = ? , ITEM_CLASSIFICATION = ?
			 * , UNIT_OF_STOCK = ? , LOSS_RATE = ? , LEAD_TIME = ? , STANDARD_UNIT_PRICE = ?
			 * , DESCRIPTION = ? WHERE ITEM_CODE = ?
			 */
			query.append("UPDATE ITEM SET\r\n" + "ITEM_NAME = ? , ITEM_GROUP_CODE = ? , ITEM_CLASSIFICATION = ? , \r\n"
					+ "UNIT_OF_STOCK = ? , LOSS_RATE = ? , LEAD_TIME = ? , \r\n"
					+ "STANDARD_UNIT_PRICE = ? , DESCRIPTION = ? \r\n" + "WHERE ITEM_CODE = ?");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getItemName());
			pstmt.setString(2, bean.getItemGroupCode());
			pstmt.setString(3, bean.getItemClassification());
			pstmt.setString(4, bean.getUnitOfStock());
			pstmt.setString(5, bean.getLossRate());
			pstmt.setString(6, bean.getLeadTime());
			pstmt.setInt(7, bean.getStandardUnitPrice());
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getItemCode());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

	public void deleteItem(ItemTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			/*
			 * DELETE FROM ITEM WHERE ITEM_CODE = ?
			 */
			query.append("DELETE FROM ITEM WHERE ITEM_CODE = ?");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, bean.getItemCode());

			rs = pstmt.executeQuery();

		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());

		} finally {

			dataSourceTransactionManager.close(pstmt, rs);

		}
	}

}