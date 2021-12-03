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
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allHRQuery);
            while (rs.next()) {
                String HR_ID = rs.getString("HR_ID");
                String HR_Name = rs.getString("HR_Name");
                String HR_EmailID = rs.getString("HR_EmailID");
                String HR_Password = rs.getString("HR_Password");
                String HR_Salary = rs.getString("HR_Salary");

                modelHR.put(HR_ID, new HR(HR_ID, HR_Name, HR_EmailID, HR_Password, HR_Salary));
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

    public boolean insert(HR e ) throws SQLException, ClassNotFoundException {
        String query=DBQueriesConstant.insertHRQuery+" values('"+e.getHR_ID()+"','"
                +e.getHR_Name()+"','"+e.getHR_EmailID()+"','"+e.getHR_Password()+"','"+e.getHR_Salary()+"')";
        Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        if(insertStatus){
            modelHR.put(e.getHR_ID(),e);
        }
        return insertStatus;
    }

}
