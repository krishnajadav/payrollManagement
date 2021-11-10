package com.asdc.payroll_management.ManagerRegistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.HRRegistration.HR;

class ManagerTest {

	@Test
	public void TestManagerName() {
		
		Manager mng=new Manager();	
		mng.setManager_Name("Krishna");
		assertEquals("Krishna",mng.getManager_Name());
		
	}

	@Test
	public void Testvalidate() {
		
		Manager mng=new Manager("test", "test", "test", 0)	;
		assertEquals(true,mng.validate());
		
	}
}
