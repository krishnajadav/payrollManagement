package com.asdc.payroll_management.Utility;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecription {

    String Enkey = "PayrolManagement";
	
	public String GetCipherText(String PlainText)
	{  
		 try 
	      {
		    Key keyEncripted = new SecretKeySpec(Enkey.getBytes(), "AES");
		    Cipher cipherObj = Cipher.getInstance("AES");
		    cipherObj.init(Cipher.ENCRYPT_MODE, keyEncripted);
		    byte[] encrypted = cipherObj.doFinal(PlainText.getBytes());
		    return new String(encrypted);
	      }
	      catch(Exception e) 
	      {
	         return e.getMessage();
	      }		
	}
	
	public String GetPlainText(String CipherText)
	{  
		 try 
	      {
			    Key keyDecripted = new SecretKeySpec(Enkey.getBytes(), "AES");
			    Cipher cipherObj = Cipher.getInstance("AES");
			    cipherObj.init(Cipher.DECRYPT_MODE, keyDecripted);	                    
	            String decrypted = new String(cipherObj.doFinal(CipherText.getBytes()));
	            return decrypted;
	      }
	      catch(Exception e) 
	      {
	         return e.getMessage();
	      }		
	}	
	
}
