package com.project.OstrichCompany.logistics.sales.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.OstrichCompany.logistics.sales.dao.DeliveryResultDAO;
import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;

public class DeliveryApplicationServiceImpl implements DeliveryApplicationService {


	private DeliveryResultDAO deliveryResultDAO;
	
	public void setDeliveryResultDAO(DeliveryResultDAO deliveryResultDAO) {
		this.deliveryResultDAO = deliveryResultDAO;
	}

	@Override
	public List<DeliveryResultTO> getDeliveyResultList() {

		List<DeliveryResultTO> deliveyResultList = deliveryResultDAO.selectDeliveyResultList();
		return deliveyResultList;
	}

	@Override
	public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryResultTO> deliveryTOList) {

		
		HashMap<String, Object> resultMap = new HashMap<>();

			ArrayList<String> insertList = new ArrayList<>();
			ArrayList<String> updateList = new ArrayList<>();
			ArrayList<String> deleteList = new ArrayList<>();

			for (DeliveryResultTO bean : deliveryTOList) {

				String status = bean.getStatus();

				switch (status.toUpperCase()) {

				case "INSERT":

					// 새로운 일련번호 생성
					String newDeliveryNo = "새로운";

					// Bean 에 새로운 일련번호 세팅
					bean.setDeliveryNo(newDeliveryNo);
					deliveryResultDAO.insertDeliveryResult(bean);
					insertList.add(newDeliveryNo);

					break;

				case "UPDATE":

					deliveryResultDAO.updateDeliveryResult(bean);

					updateList.add(bean.getDeliveryNo());

					break;

				case "DELETE":

					deliveryResultDAO.deleteDeliveryResult(bean);

					deleteList.add(bean.getDeliveryNo());

					break;

				}

			}

			resultMap.put("INSERT", insertList);
			resultMap.put("UPDATE", updateList);
			resultMap.put("DELETE", deleteList);
		return resultMap;
	}

}
