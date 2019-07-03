package com.project.OstrichCompany.logistics.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.logistics.sales.serviceFacade.SalesServiceFacade;
import com.project.OstrichCompany.logistics.sales.to.DeliveryResultTO;

public class DeliveryController extends MultiActionController {

	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	// serviceFacade 참조변수 선언
	SalesServiceFacade salesSF;

	public void setSalesSF(SalesServiceFacade salesSF) {
		this.salesSF = salesSF;
	}

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 변환

	public ModelAndView searchDeliveryList(HttpServletRequest request, HttpServletResponse response) {

		try {

			List<DeliveryResultTO> deliveyResultList = salesSF.getDeliveyResultList();

			modelMap.put("gridRowJson", deliveyResultList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	// batchListProcess

	public ModelAndView batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		String batchList = request.getParameter("batchList");

		try {

			List<DeliveryResultTO> deliveryTOList = gson.fromJson(batchList,
					new TypeToken<ArrayList<DeliveryResultTO>>() {
					}.getType());

			System.out.println(deliveryTOList);

			HashMap<String, Object> resultMap = salesSF.batchDeliveryListProcess(deliveryTOList);

			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (DataAccessException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());

		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

}
