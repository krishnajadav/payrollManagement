package com.asdc.payroll_management.LeaveManagement.config;

import com.asdc.payroll_management.LeaveManagement.Database;
import com.asdc.payroll_management.LeaveManagement.DatabaseDML;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class DatabaseFactory {

    public Database makeDB() {
        return new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT");
    }
}
