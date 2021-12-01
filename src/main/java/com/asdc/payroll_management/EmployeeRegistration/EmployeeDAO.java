package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.Database.MySQLDB;

public class EmployeeDAO implements IEmployeeDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public String saveEmployee(Employee emp)
    {	
		try {
			
			mySQLDB.LoadDatabase();
			String callST="{call SP_saveEmployee('"+emp.getEmployee_ID()+"','"+
					emp.getEmployee_Name()+"','"+emp.getEmployee_emailID()+"','"+
					emp.getEncriptedPassword(emp.getEmployee_Password())+"','"+emp.getEmployee_Address()+"','"+
					emp.getEmployee_phoneNumb()+"')}";
			 ResultSet rs=mySQLDB.ExecuteQuery(callST);
			 rs.next();	
			   if(rs.getString("CheckStatus").equals("UserExist"))
			   {
				   return "This user already Exist";  
			   }
			   else if(rs.getString("CheckStatus").equals("WrongCode"))
			   {
				   return "Wrong employee code"; 
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
