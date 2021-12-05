package com.asdc.payroll_management.DataBaseCache;

public class HR {
    private String HR_ID ;
    private String HR_Name ;
    private String HR_EmailID ;
    private String HR_Password ;
    private String HR_Salary ;

    public HR(String HR_ID, String HR_Name, String HR_EmailID, String HR_Password, String HR_Salary) {
        this.HR_ID = HR_ID;
        this.HR_Name = HR_Name;
        this.HR_EmailID = HR_EmailID;
        this.HR_Password = HR_Password;
        this.HR_Salary = HR_Salary;
    }

    public String getHR_ID() {
        return HR_ID;
    }

    public void setHR_ID(String HR_ID) {
        this.HR_ID = HR_ID;
    }

    public String getHR_Name() {
        return HR_Name;
    }

    public void setHR_Name(String HR_Name) {
        this.HR_Name = HR_Name;
    }

    public String getHR_EmailID() {
        return HR_EmailID;
    }

    public void setHR_EmailID(String HR_EmailID) {
        this.HR_EmailID = HR_EmailID;
    }

    public String getHR_Password() {
        return HR_Password;
    }

    public void setHR_Password(String HR_Password) {
        this.HR_Password = HR_Password;
    }

    public String getHR_Salary() {
        return HR_Salary;
    }

    public void setHR_Salary(String HR_Salary) {
        this.HR_Salary = HR_Salary;
    }
}
