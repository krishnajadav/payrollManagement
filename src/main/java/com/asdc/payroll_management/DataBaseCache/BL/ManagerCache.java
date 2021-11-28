package com.asdc.payroll_management.DataBaseCache.BL;

import com.asdc.payroll_management.DataBaseCache.Model.Leaves;
import com.asdc.payroll_management.DataBaseCache.Model.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ManagerCache {
    private static HashMap<String, Manager> modelManagers = new HashMap<>();
    private static ManagerCache managerCache = null;

    public static ManagerCache getInstance() {
        if(managerCache==null){
            managerCache=new ManagerCache();
        }
        return managerCache;
    }

    private ManagerCache() {
        load();
    }

    public void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allManagersQuery);
            while (rs.next()) {
                String Manager_ID = rs.getString("Manager_ID");
                String Manager_Name = rs.getString("Manager_Name");
                String Manager_EmailID = rs.getString("Manager_EmailID");
                String Manager_Password = rs.getString("Manager_Password");
                String Manager_Salary = rs.getString("Manager_Salary");

                modelManagers.put(Manager_ID, new Manager(Manager_ID, Manager_Name, Manager_EmailID, Manager_Password, Manager_Salary));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String, Manager> get(){
        return modelManagers;
    }

    public Manager getOne(String id){
        return modelManagers.get(id);
    }


}
