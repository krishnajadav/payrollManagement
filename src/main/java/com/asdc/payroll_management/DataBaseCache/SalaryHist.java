package com.asdc.payroll_management.DataBaseCache;

public class SalaryHist {
    private String SH_ID;
    private String Salary;
    private String Start_Date;
    private String End_Date;
    private String Employee_ID;

    public SalaryHist(String SH_ID, String salary, String start_Date, String end_Date, String employee_ID) {
        this.SH_ID = SH_ID;
        Salary = salary;
        Start_Date = start_Date;
        End_Date = end_Date;
        Employee_ID = employee_ID;
    }

    public String getSH_ID() {
        return SH_ID;
    }

    public void setSH_ID(String SH_ID) {
        this.SH_ID = SH_ID;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        Employee_ID = employee_ID;
    }
}
