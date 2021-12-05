package com.asdc.payroll_management.Notification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotificationTest {

	@Test
	public void TestUserEmail() {
		
		Notification not=new Notification();	
		not.setUserEmail("Krishna@gmail.com");
		assertEquals("Krishna@gmail.com",not.getUserEmail());
		
	}

	@Test
	public void TestsendEmail() {
		
		Notification not=new Notification("krishnajadav78657@gmail.com","Test Email","Test Email");
		assertEquals(true,not.sendEmail());
		
	}
}
