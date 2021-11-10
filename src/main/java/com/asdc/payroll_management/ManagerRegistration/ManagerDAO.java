package com.asdc.payroll_management.ManagerRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.Database.MySQLDB;

public class ManagerDAO implements IManagerDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public List<Manager> getAllManagers() throws SQLException {
		
        List<Manager> Manager= new ArrayList<Manager>();
        mySQLDB.LoadDatabase();
        ResultSet rs= mySQLDB.ExecuteQuery("SELECT * FROM Manager;");
        
        while (rs.next()){
        	
        	Manager Mng = new Manager();
        	Mng.setManager_ID(rs.getInt("Manager_ID"));
        	Mng.setManager_EmailID(rs.getString("Manager_EmailID"));
        	Mng.setManager_Name(rs.getString("Manager_Name"));
        	Mng.setManager_Password(rs.getString("Manager_Password"));
        	
        	Manager.add(Mng);

        }
        
        return Manager;
        
    }
	
	
	@Override
    public String saveManager(Manager mng)
    {	
		try {
			mySQLDB.LoadDatabase();
			String callST="{call SP_saveManager('"+
					mng.getManager_Name()+"','"+mng.getManager_EmailID()+"','"+
					mng.getManager_Password()+"')}";
			 ResultSet rs=mySQLDB.ExecuteQuery(callST);
			 rs.next();	
			   if(rs.getString("CheckStatus").equals("UserExist"))
			   {
				   return "This user already Exist";  
			   }
			   else
			   {
				   return "Success";  
			   }
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
    }
	
}
