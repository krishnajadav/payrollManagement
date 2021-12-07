package com.asdc.payroll_management.EmployeeRegistration;

import java.util.HashMap;
import java.util.Map;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class EmployeeDAO implements IEmployeeDAO {
	@Override
	public String saveEmployee(com.asdc.payroll_management.EmployeeRegistration.Employee emp) {
		try {

			EmployeeCache employeeCache = EmployeeCache.getInstance();
			HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
			for (Map.Entry mapElement : employeeHashMap.entrySet()) {
				Employee employeeTemp = (Employee) mapElement.getValue();

				if (employeeTemp.getEmployeeEmail() == null) {
					employeeTemp.setEmployeeEmail("");
				}

				if (employeeTemp.getEmployeeEmail().equals(emp.getEmployee_emailID())) {
					return "This user already Exist";
				} else {
					if (employeeTemp.getEmployeeID().equals(emp.getEmployee_ID())
							&& employeeTemp.getEmployeeEmail().length() == 0) {
						employeeTemp.setEmployeeName(emp.getEmployee_Name());
						employeeTemp.setEmployeeEmail(emp.getEmployee_emailID());
						employeeTemp.setEmployeePassword(emp.getEncriptedPassword(emp.getEmployee_Password()));
						employeeTemp.setEmployeeAddress(emp.getEmployee_Address());
						employeeTemp.setEmployeePhoneNumb(emp.getEmployee_phoneNumb());

						String query = "UPDATE Employee SET Employee_Name='" + emp.getEmployee_Name()
								+ "',Employee_emailID='" + emp.getEmployee_emailID() + "',Employee_Password='"
								+ emp.getEncriptedPassword(emp.getEmployee_Password()) + "',Employee_Address='"
								+ emp.getEmployee_Address() + "',Employee_phoneNumb='" + emp.getEmployee_phoneNumb()
								+ "' WHERE Employee_ID='" + emp.getEmployee_ID() + "'";
						Boolean isUpdated = employeeCache.update(query, employeeTemp);
						if (isUpdated) {
							return "Success";
						} else {
							return "Can not insert this record";
						}
					}
				}
			}
			return "Wrong employee code";
		} catch (Exception e) {
			return "Error";
		}
	}
}
