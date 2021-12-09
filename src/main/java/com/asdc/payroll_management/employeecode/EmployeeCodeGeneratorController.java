package com.asdc.payroll_management.employeecode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmployeeCodeGeneratorController {

	@RequestMapping("/EmployeeCodeGeneration")
	public ModelAndView employeeCodeGenerationHomepage(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		if (!request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("super admin")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeID", request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		mv.setViewName("EmployeeCodeGeneration");
		return mv;
	}

	@RequestMapping("/getDesignations")
	public List<String> getDesignations(HttpServletRequest request) {
		if (!request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("super admin")) {
			return null;
		}
		return new GenerateEmployeeFrontEndDataDAOImpl().getDesignationsFromCache();
	}

	@RequestMapping("/getDepartments")
	public List<String> getDepartments(HttpServletRequest request) {
		if (!request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("super admin")) {
			return null;
		}
		return new GenerateEmployeeFrontEndDataDAOImpl().getDepartmentsFromCache();
	}

	@RequestMapping("/getManagerNames")
	public List<String> getManagerNames(HttpServletRequest request) {
		if (!request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("super admin")) {
			return null;
		}
		return new GenerateEmployeeFrontEndDataDAOImpl().getManagerNamesFromCache();
	}

	@RequestMapping(value = "/getEmployeeData", method = RequestMethod.POST)
	public @ResponseBody List<Object> generateAndGetEmployeeCode(@RequestBody EmployeeData employeeData,
			HttpServletRequest request) {
		if (!request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("super admin")) {
			return null;
		}
		return new GenerateEmployeeFrontEndDataDAOImpl().processInput(employeeData);
	}
}
