package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.ArrayList;
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
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_JOB_DESIGNATIONS);
            while (rs.next()) {
                String Designation_Name = rs.getString("Designation_Name");
                modelJobDesignations.add(new JobDesignation(Designation_Name));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<JobDesignation> getAllDesignations(){
        return new ArrayList<>(modelJobDesignations);
    }

}
