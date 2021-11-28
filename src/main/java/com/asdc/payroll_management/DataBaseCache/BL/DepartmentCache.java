package com.asdc.payroll_management.DataBaseCache.BL;

import com.asdc.payroll_management.DataBaseCache.Model.Department;

import java.sql.ResultSet;
import java.util.HashMap;


public class DepartmentCache {

    private static HashMap<String, Department> modelDepartments = new HashMap<>();
    private static DepartmentCache departmentFactory=null;

    public static DepartmentCache getInstance(){
        if(departmentFactory==null){
            departmentFactory=new DepartmentCache();
        }
        return departmentFactory;
    }

    private DepartmentCache() {
        load();
    }


    private void load() {

        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allDepartmentQuery);
            while (rs.next()) {
                String Department_ID = rs.getString("Department_ID");
                String Department_Name = rs.getString("Department_Name");
                String HR_ID = rs.getString("HR_ID");

                modelDepartments.put(Department_ID, new Department(Department_ID, Department_Name, HR_ID));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Department> get(){

        return modelDepartments;
    }




}
