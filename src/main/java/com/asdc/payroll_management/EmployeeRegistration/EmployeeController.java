package com.asdc.payroll_management.EmployeeRegistration;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@RequestMapping(value = "/Employee/Save", method = RequestMethod.POST)
	public String saveEmployee(@RequestBody Employee emp) {

		if (emp.validate()) {
			return new EmployeeDAO().saveEmployee(emp);
		} else {
			return "Empty";
		}
	}
}
