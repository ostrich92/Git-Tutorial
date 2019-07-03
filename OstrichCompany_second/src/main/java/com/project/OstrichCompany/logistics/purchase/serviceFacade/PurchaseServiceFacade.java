package com.project.OstrichCompany.logistics.purchase.serviceFacade;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.OstrichCompany.logistics.purchase.to.BomDeployTO;
import com.project.OstrichCompany.logistics.purchase.to.BomInfoTO;
import com.project.OstrichCompany.logistics.purchase.to.BomTO;

public interface PurchaseServiceFacade {

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode);
	
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable();
	
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList);
	
}
