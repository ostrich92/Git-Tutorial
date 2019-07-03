package com.project.OstrichCompany.common.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import com.project.OstrichCompany.common.exception.DataAccessException;


public class DataAccessExceptionThrowsAdvice implements ThrowsAdvice {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void afterThrowing(DataAccessException ex) throws Throwable {

		if (logger.isDebugEnabled()) {
			logger.debug("DataAccessException afterThrowing 시작 : " + ex.getClass().getName() + " 예외 잡힘");
		}

		logger.error(ex.getMessage());

		if (logger.isDebugEnabled()) {
			logger.debug("DataAccessException afterThrowing 종료");
		}

		throw ex;
	}
	
	public void afterThrowing(Exception ex) throws Throwable {

		if (logger.isDebugEnabled()) {
			logger.debug("Exception afterThrowing 시작 : " + ex.getClass().getName() + " 예외 잡힘");
		}

		logger.error(ex.getMessage());

		if (logger.isDebugEnabled()) {
			logger.debug("Exception afterThrowing 종료");
		}

		throw ex;
	}
	
	
		
	

}
