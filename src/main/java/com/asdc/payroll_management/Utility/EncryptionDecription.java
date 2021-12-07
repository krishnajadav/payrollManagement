package com.asdc.payroll_management.Utility;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecription {

    final String encryptionkey = "PayrolManagement";
	
	public String GetCipherText(String PlainText)
	{  
		 try 
	      {
		    Key keyEncripted = new SecretKeySpec(encryptionkey.getBytes(), "AES");
		    Cipher cipherObject = Cipher.getInstance("AES");
		    cipherObject.init(Cipher.ENCRYPT_MODE, keyEncripted);
		    byte[] encrypted = cipherObject.doFinal(PlainText.getBytes());
		    return new String(encrypted);
	      }
	      catch(Exception e) 
	      {
	         return e.getMessage();
	      }		
	}	
}
