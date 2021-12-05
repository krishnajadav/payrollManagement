package com.asdc.payroll_management.employeecode;

import com.asdc.payroll_management.db.DBUtils;

public class GenerateEmployeeCode {

	private GenerateEmployeeCode() {
		
	}

	public static String generate() {
		String employeeCode = new String("0");
		for (String employeeNumber : DBUtils.getEmployeeCodes()) {
			if (Integer.valueOf(employeeCode) < Integer.valueOf(employeeNumber)) {
				employeeCode = employeeNumber;
			}
		}
		return String.valueOf(Integer.valueOf(employeeCode) + 1);
	}
}
