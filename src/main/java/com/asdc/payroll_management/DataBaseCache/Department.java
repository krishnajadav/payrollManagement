package com.asdc.payroll_management.DataBaseCache;

public class Department {
    private String Department_ID;
    private String Department_Name;
    private String HR_ID;

    public Department(String department_ID, String department_Name, String HR_ID) {
        Department_ID = department_ID;
        Department_Name = department_Name;
        this.HR_ID = HR_ID;
    }

    public String getDepartment_ID() {
        return Department_ID;
    }

    public void setDepartment_ID(String department_ID) {
        Department_ID = department_ID;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public String getHR_ID() {
        return HR_ID;
    }

    public void setHR_ID(String HR_ID) {
        this.HR_ID = HR_ID;
    }
}
