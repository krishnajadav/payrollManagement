package com.asdc.payroll_management.DataBaseCache;

public class Employee {
    private String Employee_ID;
    private String Employee_Name;
    private String Employee_emailID;
    private String Employee_Password;
    private String Employee_Address;
    private String Employee_phoneNumb;
    private String Employee_Salary;
    private String ManagerID;
    private String Department_ID;
    private String Designation;
    private String Access_level;

    public Employee(String employee_ID, String employee_Name, String employee_emailID, String employee_Password, String employee_Address, String employee_phoneNumb, String employee_Salary, String managerID, String department_ID, String Designation,String Access_level) {
        Employee_ID = employee_ID;
        Employee_Name = employee_Name;
        Employee_emailID = employee_emailID;
        Employee_Password = employee_Password;
        Employee_Address = employee_Address;
        Employee_phoneNumb = employee_phoneNumb;
        Employee_Salary = employee_Salary;
        ManagerID = managerID;
        Department_ID = department_ID;
        this.Designation = Designation;
        this.Access_level=Access_level;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        Employee_ID = employee_ID;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getEmployee_emailID() {
        return Employee_emailID;
    }

    public void setEmployee_emailID(String employee_emailID) {
        Employee_emailID = employee_emailID;
    }

    public String getEmployee_Password() {
        return Employee_Password;
    }

    public void setEmployee_Password(String employee_Password) {
        Employee_Password = employee_Password;
    }

    public String getEmployee_Address() {
        return Employee_Address;
    }

    public void setEmployee_Address(String employee_Address) {
        Employee_Address = employee_Address;
    }

    public String getEmployee_phoneNumb() {
        return Employee_phoneNumb;
    }

    public void setEmployee_phoneNumb(String employee_phoneNumb) {
        Employee_phoneNumb = employee_phoneNumb;
    }

    public String getEmployee_Salary() {
        return Employee_Salary;
    }

    public void setEmployee_Salary(String employee_Salary) {
        Employee_Salary = employee_Salary;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    public String getDepartment_ID() {
        return Department_ID;
    }

    public void setDepartment_ID(String department_ID) {
        Department_ID = department_ID;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getAccess_level() {
        return Access_level;
    }

    public void setAccess_level(String access_level) {
        Access_level = access_level;
    }

}
