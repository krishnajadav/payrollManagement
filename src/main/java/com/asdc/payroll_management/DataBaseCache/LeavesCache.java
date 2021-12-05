package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;


public class LeavesCache {
    private static HashMap<String, Leaves> modelLeaves = new HashMap<>();
    private static LeavesCache leavesCache = null;

    public static LeavesCache getInstance(){
        if(leavesCache==null){
            leavesCache=new LeavesCache();
        }
        return leavesCache;
    }

    private LeavesCache(){
        load();
    }


    private void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allLeavesQuery);
            while (rs.next()) {
                String Leaves_ID = rs.getString("Leaves_ID");
                String Leaves_Name = rs.getString("Leaves_Name");
                String Leaves_DuartionLimit = rs.getString("Leaves_DuartionLimit");

                modelLeaves.put(Leaves_ID, new Leaves(Leaves_ID, Leaves_Name, Leaves_DuartionLimit));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Leaves> get(){
        return new HashMap<>(modelLeaves);
    }

}
