package com.asdc.payroll_management.Notification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mockStatic;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

class NotificationTest {

	@Test
	void testNotificationExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.Notification.Notification");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}

	@Test
	public void TestUserEmail() {
		try {
			Notification notification = new Notification();
			notification.setUserEmail("Krishna@gmail.com");
			assertEquals("Krishna@gmail.com", notification.getUserEmail());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSendEmail() {
		try {
			Notification notification = new Notification();
			notification.setUserEmail("Krishna@gmail.com");
			notification.setSubject("Hii");
			notification.setMessage("Hello");
			JavaMailSender javaMail = Mockito.mock(JavaMailSender.class);
			MockedStatic<Notification> mocked = mockStatic(Notification.class);
			mocked.when(Notification::getJavaMailSender).thenReturn(javaMail);
			assertEquals(true, notification.sendEmail());
			mocked.close();
		} catch (Exception e) {
			fail();
		}
	}
}
