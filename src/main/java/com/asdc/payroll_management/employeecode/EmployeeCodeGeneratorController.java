package com.asdc.payroll_management.employeecode;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmployeeCodeGeneratorController {

	@RequestMapping("/EmployeeCodeGeneration")
	public ModelAndView employeeCodeGeneration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("EmployeeCodeGeneration");
		return mv;
	}

	@RequestMapping(value = "/getEmployeeData", method = RequestMethod.POST)
	public @ResponseBody EmployeeData employeeCodeGenerator(@RequestBody EmployeeData employeeData) {
		employeeData.setEmployeeID(GenerateEmployeeCode.generate());
		employeeData.validate();
		return employeeData;
	}
}
