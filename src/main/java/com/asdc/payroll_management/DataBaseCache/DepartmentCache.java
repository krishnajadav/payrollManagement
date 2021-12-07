package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;

public class DepartmentCache {

	private static HashMap<String, Department> modelDepartments = new HashMap<>();
	private static DepartmentCache departmentFactory = null;

	public static DepartmentCache getInstance() {
		if (departmentFactory == null) {
			departmentFactory = new DepartmentCache();
		}
		return departmentFactory;
	}

	private DepartmentCache() {
		load();
	}

	private void load() {

		try {
			ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_DEPARTMENT_QUERY);
			while (rs.next()) {
				String Department_ID = rs.getString("Department_ID");
				String Department_Name = rs.getString("Department_Name");
				String HR_ID = rs.getString("HR_ID");

				modelDepartments.put(Department_ID, new Department(Department_ID, Department_Name, HR_ID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Department> getDepartments() {

		return new HashMap<String, Department>(modelDepartments);
	}

}
