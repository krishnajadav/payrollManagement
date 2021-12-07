package com.asdc.payroll_management.DataBaseCache;

public class HR {
    private String hrId;
    private String hrName;
    private String hrEmail;
    private String hrPassword;
    private String hrSalary;

    public HR(String hrId, String hrName, String hrEmail, String hrPassword, String hrSalary) {
        this.hrId = hrId;
        this.hrName = hrName;
        this.hrEmail = hrEmail;
        this.hrPassword = hrPassword;
        this.hrSalary = hrSalary;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getHrPassword() {
        return hrPassword;
    }

    public void setHrPassword(String hrPassword) {
        this.hrPassword = hrPassword;
    }

    public String getHrSalary() {
        return hrSalary;
    }

    public void setHrSalary(String hrSalary) {
        this.hrSalary = hrSalary;
    }
}
