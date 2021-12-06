package com.asdc.payroll_management.UserAuthentication;

import java.util.HashMap;
import java.util.Map;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class UserAuthenticationDAO implements IUserAuthenticationDAO {

	@Override
	public String checkUserAuthentication(UserAuthentication ua) {
		try {

			EmployeeCache employeeCache = EmployeeCache.getInstance();
			HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
			for (Map.Entry mapElement : employeeHashMap.entrySet()) {
				Employee employeeTemp = (Employee) mapElement.getValue();

				if (employeeTemp.getEmployee_emailID() == null) {
					employeeTemp.setEmployee_emailID("");
				}
				if (employeeTemp.getEmployee_emailID().equalsIgnoreCase(ua.getUserEmail())
						&& employeeTemp.getEmployee_Password().equals(ua.getEncriptedPassword(ua.getUserPassword()))) {
					return employeeTemp.getEmployee_ID() + "#" + employeeTemp.getEmployee_Name() + "#"
							+ employeeTemp.getAccess_level();
				}
			}
			return "Invalid User";
		} catch (Exception e) {
			return "Empty";
		}
	}

}
