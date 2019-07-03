package com.project.OstrichCompany.logistics.sales.dao;

import java.util.List;

import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;

public interface DeliveryResultDAO {

	public List<DeliveryResultTO> selectDeliveyResultList();
	
	public void insertDeliveryResult(DeliveryResultTO TO);

	public void updateDeliveryResult(DeliveryResultTO TO);

	public void deleteDeliveryResult(DeliveryResultTO TO);
}
