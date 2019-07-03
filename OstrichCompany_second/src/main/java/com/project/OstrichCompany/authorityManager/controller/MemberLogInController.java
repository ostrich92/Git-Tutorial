package com.project.OstrichCompany.authorityManager.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.project.OstrichCompany.authorityManager.exception.DataNotInputException;
import com.project.OstrichCompany.authorityManager.exception.IdNotFoundException;
import com.project.OstrichCompany.authorityManager.exception.PwMissMatchException;
import com.project.OstrichCompany.authorityManager.exception.PwNotFoundException;
import com.project.OstrichCompany.authorityManager.serviceFacade.AuthorityManagerServiceFacade;
import com.project.OstrichCompany.hr.to.EmpInfoTO;

public class MemberLogInController extends MultiActionController {

	// serviceFacade 참조변수 선언
	private AuthorityManagerServiceFacade authorityManagerSF;

	public void setAuthorityManagerSF(AuthorityManagerServiceFacade authorityManagerSF) {
		this.authorityManagerSF = authorityManagerSF;
	}

	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	@SuppressWarnings("unused")
	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public ModelAndView LogInCheck(HttpServletRequest request, HttpServletResponse response) {

		String viewName = null;

		HttpSession session = request.getSession();

		String companyCode = request.getParameter("companyCode");
		String workplaceCode = request.getParameter("workplaceCode");
		String inputId = request.getParameter("userId");
		String inputPassWord = request.getParameter("userPassWord");

		try {
			if (companyCode.equals("") || workplaceCode.equals("") || inputId.equals("") || inputPassWord.equals("")) {
				throw new DataNotInputException("입력하지 않은 값이 있습니다");
			}

			EmpInfoTO bean = authorityManagerSF.accessToAuthority(companyCode, workplaceCode, inputId, inputPassWord);
			
			if (bean != null) {

				ServletContext application = request.getSession().getServletContext();

				session.setAttribute("userId", bean.getUserId());
				session.setAttribute("empCode", bean.getEmpCode());
				session.setAttribute("empName", bean.getEmpName());
				session.setAttribute("deptCode", bean.getDeptCode());
				session.setAttribute("deptName", bean.getDeptName());
				session.setAttribute("positionCode", bean.getPositionCode());
				session.setAttribute("positionName", bean.getPositionName());
				session.setAttribute("companyCode", bean.getCompanyCode());
				session.setAttribute("workplaceCode", workplaceCode);
				session.setAttribute("workplaceName", bean.getWorkplaceName());

				String menuCode = authorityManagerSF.getUserMenuCode(workplaceCode, bean.getDeptCode(),
						bean.getPositionCode(), application);
				session.setAttribute("menuCode", menuCode);

				viewName = "redirect:/hello.html";
				System.out.println("로그인 되었습니다");
			}

		} catch (DataNotInputException e1) {
			e1.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e1.getMessage());
			viewName = "loginform";
		} catch (IdNotFoundException e2) {
			e2.printStackTrace();
			modelMap.put("errorCode", -2);
			modelMap.put("errorMsg", e2.getMessage());
			viewName = "loginform";
		} catch (PwNotFoundException e3) {
			e3.printStackTrace();
			modelMap.put("errorCode", -3);
			modelMap.put("errorMsg", e3.getMessage());
			viewName = "loginform";
		} catch (PwMissMatchException e4) {
			e4.printStackTrace();
			modelMap.put("errorCode", -4);
			modelMap.put("errorMsg", e4.getMessage());
			viewName = "loginform";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errorCode", -5);
			modelMap.put("errorMsg", e.getMessage());
			viewName = "loginform";
		}

		modelAndView = new ModelAndView(viewName, modelMap);

		return modelAndView;

	}

}