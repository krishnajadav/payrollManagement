package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SalaryHistCache {
    private static HashMap<String, SalaryHist> modelSalaryHistory = new HashMap<>();
    private DatabaseConnection newDB ;

    public SalaryHistCache()  {
        newDB= DatabaseConnection.getInstance();
    }


    public void load(){
        try {
            ResultSet rs = newDB.getData(DBQueriesConstant.ALL_SALARY_HIST_QUERY);
            while (rs.next()) {
                String SH_ID = rs.getString("SH_ID");
                String Salary = rs.getString("Salary");
                String Start_Date = rs.getString("Start_Date");
                String End_Date = rs.getString("End_Date");
                String Employee_ID = rs.getString("Employee_ID");
                String Employee_Type = rs.getString("Employee_Type");

                modelSalaryHistory.put(SH_ID, new SalaryHist(SH_ID, Salary, Start_Date, End_Date, Employee_ID, Employee_Type));

            }
        }catch(Exception e){
            e.printStackTrace();
    }
    }

    public HashMap<String,SalaryHist> getAllSalaries(){

        return new HashMap<>(modelSalaryHistory);
    }

    public SalaryHist getOneSalary(String id){

        return modelSalaryHistory.get(id);
    }

}
