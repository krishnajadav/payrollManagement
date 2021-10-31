package com.asdc.payroll_management.HRRegistration;

import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HRController {

	    private HRConcreteFactory MngCF=new HRConcreteFactory();

	    @RequestMapping("/GetAllHRs")
	    public List<HR> getALLHRs() throws SQLException {
	        return MngCF.getAllHRs();
	    } 
	    
	    @RequestMapping(value = "/HR/Save",method = RequestMethod.POST)
	    public String saveHR(@ModelAttribute("HR") HR hr) {	    	
	    	return MngCF.saveHR(hr); 
	    }
	       
}
