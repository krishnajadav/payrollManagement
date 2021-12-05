package com.asdc.payroll_management.Utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.UserAuthentication.UserAuthentication;

class EncryptionDecriptionTest {

	@Test
	public void TestGetCipherText() {
		
		EncryptionDecription objEncription=new EncryptionDecription();	
		String CipherText=objEncription.GetCipherText("KR");
		assertEquals(CipherText,CipherText);
		
	}
	
	@Test
	public void TestPlainText() {
		
		EncryptionDecription objEncription=new EncryptionDecription();	
		String CipherText=objEncription.GetCipherText("Krishna");
		String PainText=objEncription.GetPlainText(CipherText);
		assertEquals(PainText,PainText);
		
	}

}
