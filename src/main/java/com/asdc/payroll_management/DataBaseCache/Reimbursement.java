package com.asdc.payroll_management.DataBaseCache;

public class Reimbursement {
    private String rtId;
    private String reimbursementType;
    private String reimbursementLimit;

    public Reimbursement(String rtId, String reimbursementType, String reimbursementLimit) {
        this.rtId = rtId;
        this.reimbursementType = reimbursementType;
        this.reimbursementLimit = reimbursementLimit;
    }

    public String getRtId() {
        return rtId;
    }

    public void setRtId(String rtId) {
        this.rtId = rtId;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public String getReimbursementLimit() {
        return reimbursementLimit;
    }

    public void setReimbursementLimit(String reimbursementLimit) {
        this.reimbursementLimit = reimbursementLimit;
    }
}
