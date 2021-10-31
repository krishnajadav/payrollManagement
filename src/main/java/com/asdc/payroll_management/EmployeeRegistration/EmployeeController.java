package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	    private EmployeeConcreteFactory EmpCF=new EmployeeConcreteFactory();

	    @RequestMapping("/GetAllEmployees")
	    public List<Employee> getALLEmployees() throws SQLException {
	        return EmpCF.getAllEmployees();
	    } 
	    
	    @RequestMapping(value = "/Employee/Save",method = RequestMethod.POST)
	    public String saveEmployee(@ModelAttribute("employee") Employee emp) {	    	
	    	return EmpCF.saveEmployee(emp); 
	    }
	       
}
