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

				if (employeeTemp.getEmployee_emailID() == null) {
					employeeTemp.setEmployee_emailID("");
				}

				if (employeeTemp.getEmployee_emailID().equals(emp.getEmployee_emailID())) {
					return "This user already Exist";
				} else {
					if (employeeTemp.getEmployee_ID().equals(emp.getEmployee_ID())
							&& employeeTemp.getEmployee_emailID().length() == 0) {
						employeeTemp.setEmployee_Name(emp.getEmployee_Name());
						employeeTemp.setEmployee_emailID(emp.getEmployee_emailID());
						employeeTemp.setEmployee_Password(emp.getEncriptedPassword(emp.getEmployee_Password()));
						employeeTemp.setEmployee_Address(emp.getEmployee_Address());
						employeeTemp.setEmployee_phoneNumb(emp.getEmployee_phoneNumb());

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
