package com.asdc.payroll_management.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	// Testing the db connection in dev environment
	public static boolean devDBConnection(){  
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con= DriverManager.getConnection(  
						"jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_DEVINT","CSCI5308_17_DEVINT_USER","aiJ9Eidoo1kieyej");
				if(con != null) {
					con.close();
					return true;
				}
				con.close();
				return false;
			}catch(Exception e){
				System.out.println(e);
				}
			return false;
	}  
}