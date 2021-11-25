package com.asdc.payroll_management.Notification;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
		
    @RequestMapping(value = "/Notification/sendEmail",method = RequestMethod.POST)
    public String sendUserEmail(@RequestBody Notification no) {	    
    	if(no.sendEmail())
    	{	    		
    		return "Success";
    	}
    	else
    	{
    		return "Error";
    	} 	
    }
	
}
