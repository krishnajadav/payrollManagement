package com.asdc.payroll_management.HRRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.Database.MySQLDB;

public class HRDAO implements IHRDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public List<HR> getAllHRs() throws SQLException {
		
        List<HR> HR= new ArrayList<HR>();
        mySQLDB.LoadDatabase();
        ResultSet rs= mySQLDB.ExecuteQuery("SELECT * FROM HR;");
        
        while (rs.next()){
        	
        	HR hr = new HR();
        	hr.sethR_ID(rs.getInt("HR_ID"));
        	hr.sethR_EmailID(rs.getString("HR_EmailID"));
        	hr.sethR_Name(rs.getString("HR_Name"));
        	hr.sethR_Password(rs.getString("HR_Password"));
        	
        	HR.add(hr);

        }
        
        return HR;
        
    }
	
	
	@Override
    public String saveHR(HR hr)
    {	
		try {
			mySQLDB.LoadDatabase();
			String callST="{call SP_saveHR('"+
					hr.gethR_Name()+"','"+hr.gethR_EmailID()+"','"+
					hr.gethR_Password()+"')}";
			mySQLDB.ExecuteQuery(callST);
			
			return "Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
    }
	
}
