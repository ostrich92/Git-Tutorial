package com.project.OstrichCompany.logistics.purchase.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.OstrichCompany.logistics.purchase.dao.BomDAO;
import com.project.OstrichCompany.logistics.purchase.to.BomDeployTO;
import com.project.OstrichCompany.logistics.purchase.to.BomInfoTO;
import com.project.OstrichCompany.logistics.purchase.to.BomTO;

public class BomApplicationServiceImpl implements BomApplicationService {

	private BomDAO bomDAO;

	public void setBomDAO(BomDAO bomDAO) {
		this.bomDAO = bomDAO;
	}

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode) {

		ArrayList<BomDeployTO> bomDeployList = bomDAO.selectBomDeployList(deployCondition, itemCode);
		return bomDeployList;

	}

	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode) {

		ArrayList<BomInfoTO> bomInfoList = bomDAO.selectBomInfoList(parentItemCode);
		return bomInfoList;

	}

	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable() {

		ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = bomDAO.selectAllItemWithBomRegisterAvailable();
		return allItemWithBomRegisterAvailable;

	}

	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList) {

		HashMap<String, Object> resultMap = new HashMap<>();

		int insertCount = 0;
		int updateCount = 0;
		int deleteCount = 0;

		for (BomTO bean : batchBomList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":

				bomDAO.insertBom(bean);

				insertCount++;

				break;

			case "UPDATE":

				bomDAO.updateBom(bean);

				updateCount++;

				break;

			case "DELETE":

				bomDAO.deleteBom(bean);

				deleteCount++;

				break;

			}

		}

		resultMap.put("INSERT", insertCount);
		resultMap.put("UPDATE", updateCount);
		resultMap.put("DELETE", deleteCount);

		return resultMap;
	}

}
