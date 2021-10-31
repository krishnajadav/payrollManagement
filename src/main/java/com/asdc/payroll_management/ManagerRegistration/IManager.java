package com.asdc.payroll_management.ManagerRegistration;

import java.util.List;

public interface IManager {

	List<Manager> getAllManagers();
	String saveManager(Manager mng);
	
}
