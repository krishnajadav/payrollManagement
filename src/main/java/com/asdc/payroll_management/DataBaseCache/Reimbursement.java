package com.asdc.payroll_management.DataBaseCache;

public class Reimbursement {
    private String RT_ID;
    private String Reimbursement_Type;
    private int Reimbursement_Limit;

    public Reimbursement(String RT_ID, String reimbursement_Type, int reimbursement_Limit) {
        this.RT_ID = RT_ID;
        Reimbursement_Type = reimbursement_Type;
        Reimbursement_Limit = reimbursement_Limit;
    }

    public String getRT_ID() {
        return RT_ID;
    }

    public void setRT_ID(String RT_ID) {
        this.RT_ID = RT_ID;
    }

    public String getReimbursement_Type() {
        return Reimbursement_Type;
    }

    public void setReimbursement_Type(String reimbursement_Type) {
        Reimbursement_Type = reimbursement_Type;
    }

    public int getReimbursement_Limit() {
        return Reimbursement_Limit;
    }

    public void setReimbursement_Limit(int reimbursement_Limit) {
        Reimbursement_Limit = reimbursement_Limit;
    }
}
