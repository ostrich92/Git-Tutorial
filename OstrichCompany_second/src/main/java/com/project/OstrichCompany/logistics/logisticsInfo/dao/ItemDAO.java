package com.project.OstrichCompany.logistics.logisticsInfo.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemInfoTO;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemTO;

public interface ItemDAO {

	public ArrayList<ItemInfoTO> selectAllItemList();
	
	public ArrayList<ItemInfoTO> selectItemList(String searchCondition, String paramArray[]);
	
	public void insertItem(ItemTO TO);
	
	public void updateItem(ItemTO TO);
	
	public void deleteItem(ItemTO TO);
	
}
