package com.asdc.payroll_management.ManagerRegistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ManagerTest {

	@Test
	public void TestManagerName() {
		
		Manager mng=new Manager();	
		mng.setManager_Name("Krishna");
		assertEquals("Krishna",mng.getManager_Name());
		
	}

}
