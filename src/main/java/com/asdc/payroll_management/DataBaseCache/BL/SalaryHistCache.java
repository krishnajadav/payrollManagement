package com.asdc.payroll_management.DataBaseCache.BL;

import com.asdc.payroll_management.DataBaseCache.Model.Reimbursement;
import com.asdc.payroll_management.DataBaseCache.Model.SalaryHist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SalaryHistCache {
    private static HashMap<String, SalaryHist> modelSalaryHistory = new HashMap<>();
    private DatabaseConnection newDB ;

    public SalaryHistCache() throws SQLException, ClassNotFoundException {
        newDB= DatabaseConnection.getInstance();
    }


    public void load() throws SQLException, ClassNotFoundException {

        ResultSet rs = newDB.getData(DBQueriesConstant.allSalaryHistQuery);
        while (rs.next()) {
            String SH_ID = rs.getString("SH_ID");
            String Salary = rs.getString("Salary");
            String Start_Date = rs.getString("Start_Date");
            String End_Date = rs.getString("End_Date");
            String Employee_ID = rs.getString("Employee_ID");
            String Employee_Type = rs.getString("Employee_Type");

            modelSalaryHistory.put(SH_ID,new SalaryHist(SH_ID,Salary,Start_Date,End_Date,Employee_ID,Employee_Type));
        }
    }

    public HashMap<String,SalaryHist> get(){

        return modelSalaryHistory;
    }

    public SalaryHist getOne(String id){

        return modelSalaryHistory.get(id);
    }

}