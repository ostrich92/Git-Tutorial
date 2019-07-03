package com.project.OstrichCompany.base.applicationService;

import java.util.ArrayList;

import com.project.OstrichCompany.base.to.AddressTO;

public interface AddressApplicationService {
		
	public ArrayList<AddressTO> getAddressList(String sidoName, String searchAddressType, String searchValue, String mainNumber);
	
}
