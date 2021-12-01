package com.asdc.payroll_management.TaxCalculation;

import java.sql.*;

import com.asdc.payroll_management.Database.MySQLDB;

public class TaxCalculationDAO implements ITaxCalculationDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public String generateTaxCalculation(TaxCalculation tc)
    {	
		try {
			
			mySQLDB.LoadDatabase();
			String callST="{call SP_userTaxCalculation('"+
					tc.getUserID()+"')}";
			ResultSet rs=mySQLDB.ExecuteQuery(callST);
			
			if (!rs.isBeforeFirst() ) {    
				return "0";	
			}
			else
			{
				rs.next();
				return rs.getString("totalSalary");
			}						
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
    }
	
}
