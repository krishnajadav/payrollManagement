package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
public class SalaryHistCache {
    private static HashMap<String, SalaryHist> modelSalaryHistory = new HashMap<>();
    private static SalaryHistCache salaryHistCache=null;


    public static SalaryHistCache getInstance(){
        if(salaryHistCache==null){
            salaryHistCache=new SalaryHistCache();
        }
        return salaryHistCache;
    }



    private SalaryHistCache()  {
        load();
    }


    private void load(){
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_SALARY_HIST_QUERY);
            while (rs.next()) {
                String SH_ID = rs.getString("SH_ID");
                String Salary = rs.getString("Salary");
                String Start_Date = rs.getString("Start_Date");
                String End_Date = rs.getString("End_Date");
                String Employee_ID = rs.getString("Employee_ID");

                modelSalaryHistory.put(SH_ID, new SalaryHist(SH_ID, Salary, Start_Date, End_Date, Employee_ID));

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
