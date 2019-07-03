package com.project.OstrichCompany.authorityManager.dao;

import java.util.HashMap;

import com.project.OstrichCompany.authorityManager.to.UserMenuTO;

public interface UserMenuDAO {

	public HashMap<String, UserMenuTO> selectUserMenuCodeList(String workplaceCode, String deptCode,
			String positionCode);

}
