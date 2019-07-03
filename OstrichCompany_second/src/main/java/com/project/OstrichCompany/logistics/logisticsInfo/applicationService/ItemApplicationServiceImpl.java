package com.project.OstrichCompany.logistics.logisticsInfo.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.OstrichCompany.base.dao.CodeDetailDAO;
import com.project.OstrichCompany.base.to.CodeDetailTO;
import com.project.OstrichCompany.logistics.logisticsInfo.dao.ItemDAO;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemInfoTO;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemTO;
import com.project.OstrichCompany.logistics.purchase.dao.BomDAO;
import com.project.OstrichCompany.logistics.purchase.to.BomTO;

public class ItemApplicationServiceImpl implements ItemApplicationService {

	// DAO 참조변수 선언
	private ItemDAO itemDAO;
	private CodeDetailDAO codeDetailDAO;
	private BomDAO bomDAO;

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public void setCodeDetailDAO(CodeDetailDAO codeDetailDAO) {
		this.codeDetailDAO = codeDetailDAO;
	}

	public void setBomDAO(BomDAO bomDAO) {
		this.bomDAO = bomDAO;
	}

	public ArrayList<ItemInfoTO> getItemInfoList(String searchCondition, String[] paramArray) {

		ArrayList<ItemInfoTO> itemInfoList = null;

		switch (searchCondition) {

		case "ALL":

			itemInfoList = itemDAO.selectAllItemList();

			break;

		case "ITEM_CLASSIFICATION":

			itemInfoList = itemDAO.selectItemList("ITEM_CLASSIFICATION", paramArray);

			break;

		case "ITEM_GROUP_CODE":

			itemInfoList = itemDAO.selectItemList("ITEM_GROUP_CODE", paramArray);

			break;

		case "STANDARD_UNIT_PRICE":

			itemInfoList = itemDAO.selectItemList("STANDARD_UNIT_PRICE", paramArray);

			break;

		}

		return itemInfoList;
	}

	public HashMap<String, Object> batchItemListProcess(ArrayList<ItemTO> itemBeanList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		CodeDetailTO detailCodeBean = new CodeDetailTO();
		BomTO bomBean = new BomTO();

		for (ItemTO bean : itemBeanList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				itemDAO.insertItem(bean);
				insertList.add(bean.getItemCode());

				// CODE_DETAIL 테이블에 Insert
				detailCodeBean.setDivisionCodeNo(bean.getItemClassification());
				detailCodeBean.setDetailCode(bean.getItemCode());
				detailCodeBean.setDetailCodeName(bean.getItemName());
				detailCodeBean.setDescription(bean.getDescription());

				codeDetailDAO.insertDetailCode(detailCodeBean);

				// 새로운 품목이 완제품 (ItemClassification : "IT-CI") , 반제품 (ItemClassification :
				// "IT-SI") 일 경우 BOM 테이블에 Insert
				if (bean.getItemClassification().equals("IT-CI") || bean.getItemClassification().equals("IT-SI")) {

					bomBean.setNo(1);
					bomBean.setParentItemCode("NULL");
					bomBean.setItemCode(bean.getItemCode());
					bomBean.setNetAmount(1);

					bomDAO.insertBom(bomBean);
				}

				break;

			case "UPDATE":

				itemDAO.updateItem(bean);

				updateList.add(bean.getItemCode());

				// CODE_DETAIL 테이블에 Update
				detailCodeBean.setDivisionCodeNo(bean.getItemClassification());
				detailCodeBean.setDetailCode(bean.getItemCode());
				detailCodeBean.setDetailCodeName(bean.getItemName());
				detailCodeBean.setDescription(bean.getDescription());

				codeDetailDAO.updateDetailCode(detailCodeBean);

				break;

			case "DELETE":

				itemDAO.deleteItem(bean);

				deleteList.add(bean.getItemCode());

				// CODE_DETAIL 테이블에 Delete
				detailCodeBean.setDivisionCodeNo(bean.getItemClassification());
				detailCodeBean.setDetailCode(bean.getItemCode());
				detailCodeBean.setDetailCodeName(bean.getItemName());
				detailCodeBean.setDescription(bean.getDescription());

				codeDetailDAO.deleteDetailCode(detailCodeBean);

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;

	}

}