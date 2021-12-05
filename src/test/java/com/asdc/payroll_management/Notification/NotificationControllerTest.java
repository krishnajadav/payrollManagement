package com.asdc.payroll_management.Notification;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class NotificationControllerTest {

	
	@Test
	public void TestsendEmail() {
		
		NotificationController notCon=new NotificationController();
		Notification not=new Notification("krishnajadav78657@gmail.com","Test Email","Test Email");
		assertEquals("Success",notCon.sendUserEmail(not));
		
	}
	
}
