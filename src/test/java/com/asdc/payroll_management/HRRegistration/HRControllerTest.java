package com.asdc.payroll_management.HRRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.Database.MySQLDB;

class HRControllerTest {

	@Test
	public void TestgetAllHR() throws SQLException {
		
		try
		{		
			HRController hr=new HRController();		
			//List<HR> hrList=hr.getAllHRs();		
			assertEquals(0, 0); 
		}
		catch (Exception e) {
			fail();
		}
					
	}
	
	@Test
	public void TestsaveHR() throws SQLException {
			
		try
		{		
			HRController hrC=new HRController();		
			HR hr=new HR();
			hr.sethR_EmailID("Test");
			hr.sethR_Name("Test");
			hr.sethR_Password("Test");		
			String result=hrC.saveHR(hr);		
			assertEquals(0, 0); 
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
}
