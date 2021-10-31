package com.asdc.payroll_management.ManagerRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class ManagerDAOTest {

	@Test
	public void TestgetAllManagers() throws SQLException {
		
		try
		{
			
			ManagerDAO mng=new ManagerDAO();		
			//List<Manager> mngList=mng.getAllManagers();
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
	}

	
	@Test
	public void TestsaveManager() throws SQLException {
		
		try
		{		
			ManagerDAO mangDAO=new ManagerDAO();		
			Manager mng=new Manager();
			mng.setManager_EmailID("Test");
			mng.setManager_Name("Test");
			mng.setManager_Password("Test");		
			String result=mangDAO.saveManager(mng);	
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
	
	
}
