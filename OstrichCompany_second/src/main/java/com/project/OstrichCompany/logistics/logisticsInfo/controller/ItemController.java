package com.project.OstrichCompany.logistics.logisticsInfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.OstrichCompany.common.exception.DataAccessException;
import com.project.OstrichCompany.logistics.logisticsInfo.serviceFacade.LogisticsInfoServiceFacade;
import com.project.OstrichCompany.logistics.logisticsInfo.serviceFacade.LogisticsInfoServiceFacadeImpl;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemInfoTO;
import com.project.OstrichCompany.logistics.logisticsInfo.to.ItemTO;

public class ItemController extends MultiActionController {

	// GSON 라이브러리
	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환

	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	// serviceFacade 참조변수 선언
	private LogisticsInfoServiceFacade logisticsInfoSF;

	public void setLogisticsInfoSF(LogisticsInfoServiceFacade logisticsInfoSF) {
		this.logisticsInfoSF = logisticsInfoSF;
	}

	public ModelAndView searchItem(HttpServletRequest request, HttpServletResponse response) {

		String searchCondition = request.getParameter("searchCondition");
		String itemClassification = request.getParameter("itemClassification");
		String itemGroupCode = request.getParameter("itemGroupCode");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");

		ArrayList<ItemInfoTO> itemInfoList = null;
		String[] paramArray = null;

		try {

			switch (searchCondition) {

			case "ALL":

				paramArray = null;
				break;

			case "ITEM_CLASSIFICATION":

				paramArray = new String[] { itemClassification };
				break;

			case "ITEM_GROUP_CODE":

				paramArray = new String[] { itemGroupCode };
				break;

			case "STANDARD_UNIT_PRICE":

				paramArray = new String[] { minPrice, maxPrice };
				break;

			}

			itemInfoList = logisticsInfoSF.getItemInfoList(searchCondition, paramArray);

			modelMap.put("gridRowJson", itemInfoList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e1.getMessage());

		}

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;

	}

	public ModelAndView batchListProcess(HttpServletRequest request, HttpServletResponse response) {

		if (logger.isDebugEnabled()) {
			logger.debug("ItemController : batchListProcess 시작");
		}

		String batchList = request.getParameter("batchList");

		ArrayList<ItemTO> itemBeanList = gson.fromJson(batchList, new TypeToken<ArrayList<ItemTO>>() {
		}.getType());

		try {

			HashMap<String, Object> resultMap = logisticsInfoSF.batchItemListProcess(itemBeanList);

			modelMap.put("result", resultMap);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "성공");

		} catch (Exception e1) {
			e1.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e1.getMessage());

		}

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;

	}

}
