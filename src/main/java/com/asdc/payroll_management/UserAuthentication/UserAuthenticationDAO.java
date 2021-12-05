package com.asdc.payroll_management.UserAuthentication;

import java.sql.*;

import com.asdc.payroll_management.Database.MySQLDB;

public class UserAuthenticationDAO implements IUserAuthenticationDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public String checkUserAuthentication(UserAuthentication ua)
    {	
		try {
			
			mySQLDB.LoadDatabase();
			String callST="{call SP_userAuthentication('"+
					ua.getUserEmail()+"','"+ ua.getEncriptedPassword(ua.getUserPassword())+"')}";
			ResultSet rs=mySQLDB.ExecuteQuery(callST);
			
			if (!rs.isBeforeFirst() ) {    
				return "Invalid User";	
			}
			else
			{
				rs.next();
				return rs.getString("userID")+"#"+rs.getString("userName")+"#"+rs.getString("Designation");
			}			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Empty";
		}
    }
	
}
