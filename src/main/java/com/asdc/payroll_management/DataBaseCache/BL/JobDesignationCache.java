package com.asdc.payroll_management.DataBaseCache.BL;

import com.asdc.payroll_management.DataBaseCache.Model.HR;
import com.asdc.payroll_management.DataBaseCache.Model.JobDesignation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobDesignationCache {
    private static List<JobDesignation> modelJobDesignations = new ArrayList<JobDesignation>();
    private static JobDesignationCache jobDesignationCache=null;

    public static JobDesignationCache getInstance(){
        if(jobDesignationCache==null){
            jobDesignationCache=new JobDesignationCache();
        }
        return jobDesignationCache;
    }

    private JobDesignationCache(){
        load();
    }

    private void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allJobDesignations);
            while (rs.next()) {
                String Designation_Name = rs.getString("Designation_Name");
                modelJobDesignations.add(new JobDesignation(Designation_Name));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<JobDesignation> get(){
        return modelJobDesignations;
    }

}
