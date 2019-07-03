package com.project.OstrichCompany.logistics.sales.applicationService;

import java.util.HashMap;
import java.util.List;

import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;

public interface DeliveryApplicationService {

	public List<DeliveryResultTO> getDeliveyResultList();

	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryResultTO> deliveryTOList);
}
