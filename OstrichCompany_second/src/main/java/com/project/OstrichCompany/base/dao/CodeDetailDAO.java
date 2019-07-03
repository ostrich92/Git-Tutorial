package com.project.OstrichCompany.base.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.base.to.CodeDetailTO;

public interface CodeDetailDAO {

	ArrayList<CodeDetailTO> selectDetailCodeList(String divisionCode);

	void insertDetailCode(CodeDetailTO bean);

	void updateDetailCode(CodeDetailTO bean);

	public void deleteDetailCode(CodeDetailTO bean);

	public void changeCodeUseCheck(String divisionCodeNo, String detailCode, String codeUseCheck);

}
