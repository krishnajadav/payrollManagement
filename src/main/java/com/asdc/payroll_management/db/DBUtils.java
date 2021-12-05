package com.asdc.payroll_management.db;

import java.util.ArrayList;

public class DBUtils {
	
	public static ArrayList<String> getEmployeeCodes(){
		ArrayList<String> employeeCodes = new ArrayList<String>();
		employeeCodes.add("1");
		employeeCodes.add("2");
		employeeCodes.add("3");
		return employeeCodes;
	}
	
	public static ArrayList<String> getDesignations(){
		ArrayList<String> designations = new ArrayList<String>();
		designations.add("SE 1");
		designations.add("SE 2");
		designations.add("SE 3");
		return designations;
	}

}
