package com.asdc.payroll_management.TaxCalculation;

import java.util.HashMap;
import java.util.Map;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class TaxCalculationDAO implements ITaxCalculationDAO {

	@Override
	public String generateTaxCalculation(TaxCalculation tc) {
		try {
			EmployeeCache employeeCache = EmployeeCache.getInstance();
			HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
			Double totalSalary = 0.0;
			for (Map.Entry mapElement : employeeHashMap.entrySet()) {
				Employee employeeTemp = (Employee) mapElement.getValue();
				if (employeeTemp.getEmployeeID().equals(tc.getUserID())) {
					if(employeeTemp.getEmployeeSalary()==null || employeeTemp.getEmployeeSalary()=="")
					{
						totalSalary=0.0;
					}
					else
					{
						totalSalary=Double.parseDouble(employeeTemp.getEmployeeSalary());
					}					 	
				}
			}
			return totalSalary.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
