package com.asdc.payroll_management.DataBaseCache;

public class SalaryHist {
    private String shId;
    private String salary;
    private String startDate;
    private String endDate;
    private String employeeID;
    private String employeeType;

    public SalaryHist(String shId, String salary, String startDate, String endDate, String employeeID, String employeeType) {
        this.shId = shId;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeID = employeeID;
        this.employeeType = employeeType;
    }

    public String getShId() {
        return shId;
    }

    public void setShId(String shId) {
        this.shId = shId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
