package com.asdc.payroll_management.DataBaseCache;

public class Manager {
    private String Manager_ID;
    private String Manager_Name;
    private String Manager_EmailID;
    private String Manager_Password;
    private String Manager_Salary;

    public Manager(String manager_ID, String manager_Name, String manager_EmailID, String manager_Password, String manager_Salary) {
        Manager_ID = manager_ID;
        Manager_Name = manager_Name;
        Manager_EmailID = manager_EmailID;
        Manager_Password = manager_Password;
        Manager_Salary = manager_Salary;
    }

    public String getManager_ID() {
        return Manager_ID;
    }

    public void setManager_ID(String manager_ID) {
        Manager_ID = manager_ID;
    }

    public String getManager_Name() {
        return Manager_Name;
    }

    public void setManager_Name(String manager_Name) {
        Manager_Name = manager_Name;
    }

    public String getManager_EmailID() {
        return Manager_EmailID;
    }

    public void setManager_EmailID(String manager_EmailID) {
        Manager_EmailID = manager_EmailID;
    }

    public String getManager_Password() {
        return Manager_Password;
    }

    public void setManager_Password(String manager_Password) {
        Manager_Password = manager_Password;
    }

    public String getManager_Salary() {
        return Manager_Salary;
    }

    public void setManager_Salary(String manager_Salary) {
        Manager_Salary = manager_Salary;
    }
}
