package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	    private EmployeeConcreteFactory EmpCF=new EmployeeConcreteFactory();
    
	    @RequestMapping(value = "/Employee/Save",method = RequestMethod.POST)
	    public String saveEmployee(@RequestBody Employee emp) {	    	
	    
	    	if(emp.validate())
	    	{
	    		return EmpCF.saveEmployee(emp);  
	    	}
	    	else
	    	{
	    		return "Empty";
	    	}
	    }	          
}
