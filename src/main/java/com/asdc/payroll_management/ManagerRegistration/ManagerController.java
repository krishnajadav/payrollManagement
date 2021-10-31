package com.asdc.payroll_management.ManagerRegistration;

import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
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
	    public String saveManager(@ModelAttribute("manager") Manager mng) {	    	
	    	return MngCF.saveManager(mng); 
	    }
	       
}
