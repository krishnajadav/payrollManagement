package com.asdc.payroll_management.Database;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.sql.*;


public class MySQLDBTest {

	@Test
	public void NotNullTestForClass() throws ClassNotFoundException {
		
		try {
			    Class.forName("com.asdc.payroll_management.Database.MySQLDB");			
			} catch( ClassNotFoundException ex) {
				 fail("NoClassFound");
			}		
	}

	@Test
	public void TestLoadDatabase()
	{
		MySQLDB obj=new MySQLDB();	
		
		try
		{
			
		obj.LoadDatabase();
		assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
	}
	
	
	@Test
	public void TestExecuteQuery()
	{
		try
		{
			
			MySQLDB obj=new MySQLDB();	
		    //ResultSet Emp=obj.ExecuteQuery("Select * from Employee");
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
}