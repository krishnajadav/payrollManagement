package com.asdc.payroll_management.UserAuthentication;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticationController {

	    private UserAuthenticationConcreteFactory UACF=new UserAuthenticationConcreteFactory();
	    
	    @RequestMapping(value = "/UserAuthentication/check",method = RequestMethod.POST)
	    public String checkUserAuthentication(@ModelAttribute("userAuthentication") UserAuthentication ua) {	    	
	    	return UACF.checkUserAuthentication(ua); 
	    }
	       
}
