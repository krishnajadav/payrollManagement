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

				if (employeeTemp.getEmployeeEmail() == null) {
					employeeTemp.setEmployeeEmail("");
				}
				if (employeeTemp.getEmployeeEmail().equalsIgnoreCase(ua.getUserEmail())
						&& employeeTemp.getEmployeePassword().equals(ua.getEncriptedPassword(ua.getUserPassword()))) {
					return employeeTemp.getEmployeeID() + "#" + employeeTemp.getEmployeeName() + "#"
							+ employeeTemp.getAccessLevel();
				}
			}
			return "Invalid User";
		} catch (Exception e) {
			return "Empty";
		}
	}

}
