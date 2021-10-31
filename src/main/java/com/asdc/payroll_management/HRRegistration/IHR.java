package com.asdc.payroll_management.HRRegistration;

import java.util.List;

public interface IHR {

	List<HR> getAllHRs();
	String saveHR(HR hr);
	
}
