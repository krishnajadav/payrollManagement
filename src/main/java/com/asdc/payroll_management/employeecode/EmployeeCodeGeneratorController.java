package com.asdc.payroll_management.employeecode;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmployeeCodeGeneratorController {

	@RequestMapping("/EmployeeCodeGeneration")
	public ModelAndView employeeCodeGenerationHomepage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("EmployeeCodeGeneration");
		return mv;
	}
	
	@RequestMapping("/getDesignations")
	public List<String> getDesignations() {
		return new GenerateEmployeeFrontEndDataDAOImpl().getDesignationsFromCache();
	}
	
	@RequestMapping("/getDepartments")
	public List<String> getDepartments() {
		return new GenerateEmployeeFrontEndDataDAOImpl().getDepartmentsFromCache();
	}
	
	@RequestMapping("/getManagerNames")
	public List<String> getManagerNames() {
		return new GenerateEmployeeFrontEndDataDAOImpl().getManagerNamesFromCache();
	}

	@RequestMapping(value = "/getEmployeeData", method = RequestMethod.POST)
	public @ResponseBody List<Object> generateAndGetEmployeeCode(@RequestBody EmployeeData employeeData) {
		return new GenerateEmployeeFrontEndDataDAOImpl().processInput(employeeData);
	}
}
