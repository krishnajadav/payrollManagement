package com.asdc.payroll_management.DataBaseCache;

public class Employee {
    private String employeeID;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
    private String employeeAddress;
    private String employeePhoneNumb;
    private String employeeSalary;
    private String managerID;
    private String departmentID;
    private String designation;
    private String accessLevel;

    public Employee(String employeeID, String employeeName, String employeeEmailID, String employeePassword, String employeeAddress, String employeePhoneNumb, String employeeSalary, String managerID, String departmentID, String designation, String accessLevel) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        employeeEmail = employeeEmailID;
        this.employeePassword = employeePassword;
        this.employeeAddress = employeeAddress;
        this.employeePhoneNumb = employeePhoneNumb;
        this.employeeSalary = employeeSalary;
        this.managerID = managerID;
        this.departmentID = departmentID;
        this.designation = designation;
        this.accessLevel = accessLevel;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeePhoneNumb() {
        return employeePhoneNumb;
    }

    public void setEmployeePhoneNumb(String employeePhoneNumb) {
        this.employeePhoneNumb = employeePhoneNumb;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String Designation) {
        this.designation = Designation;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

}
