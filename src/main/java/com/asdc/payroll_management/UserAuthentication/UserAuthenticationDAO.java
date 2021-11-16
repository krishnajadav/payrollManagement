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
					ua.getUserEmail()+"','"+ua.getUserPassword()+"','"+
					ua.getUserType()+"')}";
			ResultSet rs=mySQLDB.ExecuteQuery(callST);
			
			if (!rs.isBeforeFirst() ) {    
				return "Invalid User";	
			}
			else
			{
				rs.next();
				return rs.getString("userID")+"#"+ua.getUserType();
			}			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Empty";
		}
    }
	
}
