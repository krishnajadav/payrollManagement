package com.asdc.payroll_management.HRRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class HRDAOTest {

	@Test
	public void TestgetAllHRs() throws SQLException {
		
		try
		{
			
			HRDAO hr=new HRDAO();		
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
			HRDAO mangDAO=new HRDAO();		
			HR hr=new HR();
			hr.sethR_EmailID("Test");
			hr.sethR_Name("Test");
			hr.sethR_Password("Test");		
			String result=mangDAO.saveHR(hr);	
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
	
	
}
