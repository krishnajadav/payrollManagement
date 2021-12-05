package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HRCache {
    private static HashMap<String, HR> modelHR = new HashMap<>();
    private static HRCache hrCache = null;

    public static HRCache getInstance(){
        if(hrCache==null){
            hrCache=new HRCache();
        }
        return hrCache;
    }

    private HRCache() {
        load();
    }


    private void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_HR_QUERY);
            while (rs.next()) {
                String hrId = rs.getString("HR_ID");
                String hrName = rs.getString("HR_Name");
                String hrEmailID = rs.getString("HR_EmailID");
                String hrPassword = rs.getString("HR_Password");
                String hrSalary = rs.getString("HR_Salary");

                modelHR.put(hrId, new HR(hrId, hrName, hrEmailID, hrPassword, hrSalary));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,HR> getallHRS(){
        return new HashMap<>(modelHR);
    }

    public HR getOneHR(String id){
        return modelHR.get(id);
    }

    public boolean insert(HR hr ) throws SQLException, ClassNotFoundException {
        String query=DBQueriesConstant.INSERT_HR_QUERY +" values('"+hr.getHrId()+"','"
                +hr.getHrName()+"','"+hr.getHrEmail()+"','"+hr.getHrPassword()+"','"+hr.getHrSalary()+"')";
        Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        if(insertStatus){
            modelHR.put(hr.getHrId(),hr);
        }
        return insertStatus;
    }

}
