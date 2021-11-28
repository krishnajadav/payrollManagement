package com.asdc.payroll_management.DataBaseCache.Model;

public class Leaves {
    private String Leaves_ID;
    private String Leaves_Name;
    private String Leaves_DuartionLimit;

    public Leaves(String leaves_ID, String leaves_Name, String leaves_DuartionLimit) {
        Leaves_ID = leaves_ID;
        Leaves_Name = leaves_Name;
        Leaves_DuartionLimit = leaves_DuartionLimit;
    }

    public String getLeaves_ID() {
        return Leaves_ID;
    }

    public void setLeaves_ID(String leaves_ID) {
        Leaves_ID = leaves_ID;
    }

    public String getLeaves_Name() {
        return Leaves_Name;
    }

    public void setLeaves_Name(String leaves_Name) {
        Leaves_Name = leaves_Name;
    }

    public String getLeaves_DuartionLimit() {
        return Leaves_DuartionLimit;
    }

    public void setLeaves_DuartionLimit(String leaves_DuartionLimit) {
        Leaves_DuartionLimit = leaves_DuartionLimit;
    }
}
