package com.asdc.payroll_management.DataBaseCache;

public class Department {
    private String departmentID;
    private String departmentName;
    private String hrId;

    public Department(String departmentID, String departmentName, String hrId) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.hrId = hrId;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }
}
