package com.asdc.payroll_management;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.payroll_management.db.DBConnect;

@RestController
public class PayrollManagementController {


    @GetMapping("/{name}")
    public String helloWorld(@PathVariable String name, @RequestParam(name = "last") String lastName) {
        if(DBConnect.devDBConnection()) {
        	return "Hey "+name+ " "+ lastName +"!";
        }
    	return "";
    }

}
