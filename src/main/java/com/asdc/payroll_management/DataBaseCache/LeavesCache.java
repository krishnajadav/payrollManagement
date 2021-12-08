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
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_LEAVES_QUERY);
            while (rs.next()) {
                String leavesId = rs.getString("Leaves_ID");
                String leavesName = rs.getString("Leaves_Name");
                String leavesDuartionLimit = rs.getString("Leaves_DuartionLimit");

                modelLeaves.put(leavesId, new Leaves(leavesId, leavesName, leavesDuartionLimit));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Leaves> getAllLeaveTypes(){
        return new HashMap<>(modelLeaves);
    }

}
