package com.asdc.payroll_management.ManagerRegistration;

import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	    private ManagerConcreteFactory MngCF=new ManagerConcreteFactory();

	    @RequestMapping("/GetAllManagers")
	    public List<Manager> getALLManagers() throws SQLException {
	        return MngCF.getAllManagers();
	    } 
	    
	    @RequestMapping(value = "/Manager/Save",method = RequestMethod.POST)
	    public String saveManager(@RequestBody Manager mng) {	    	
	    
	    	if(mng.validate())
	    	{
	    		System.out.println(mng.getManager_EmailID()+"dsfs"); 
	    		return MngCF.saveManager(mng); 
	    	}
	    	else
	    	{
	    		return "Empty";
	    	}
	    }
	       
}
