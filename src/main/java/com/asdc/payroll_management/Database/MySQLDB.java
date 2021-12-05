package com.asdc.payroll_management.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDB implements IDB {


    private Connection con;
    private ResultSet rs;
    private Statement st;

    @Override
    public void LoadDatabase(){
        	
            try {
                con = DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_DEVINT","CSCI5308_17_DEVINT_USER","aiJ9Eidoo1kieyej");
            } catch (SQLException e) {
                e.printStackTrace();
            }           
            
    }

	@Override
	public ResultSet ExecuteQuery(String query) {
		
		if(con==null)
		{
			LoadDatabase();
		}
		
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);                        
         
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

	}
	
	@Override
	public void Close() {
		
		try {
			
			rs.close();
			st.close();
		    con.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
