package com.project.OstrichCompany.base.dao;

import java.util.ArrayList;

import com.project.OstrichCompany.base.to.CodeTO;

public interface CodeDAO {

	public ArrayList<CodeTO> selectCodeList();

	public void insertCode(CodeTO codeBean);

	public void updateCode(CodeTO codeBean);

	public void deleteCode(CodeTO codeBean);

}
