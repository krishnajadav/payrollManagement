package com.asdc.payroll_management.Utility;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.UserAuthentication.UserAuthentication;

class EncryptionDecriptionTest {
	EncryptionDecription testObject = new EncryptionDecription();
	@Test
	void testEncryptionDecriptionExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.Utility.EncryptionDecription");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	@Test
	public void testGetCipherText() {
		String CipherText="";
		CipherText = testObject.GetCipherText("KR");
		assertEquals("—­Å+R:…þ?Ín¦@", CipherText);
		CipherText = testObject.GetCipherText("Hello");
		assertNotEquals("—­Å+R:…þ?Ín¦@", CipherText);
		assertNotNull("—­Å+R:…þ?Ín¦@", CipherText);
	}
	@Test
	public void testPlainText() {
		String CipherText="",PainText="";
		CipherText = testObject.GetCipherText("KR");
		PainText = testObject.GetPlainText(CipherText);
		assertEquals("KR", PainText);	
		CipherText = testObject.GetCipherText("dd");
		PainText = testObject.GetPlainText(CipherText);
		assertEquals("dd", PainText);
		assertNotEquals("Hii", PainText);
	}

}
