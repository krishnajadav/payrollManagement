package com.asdc.payroll_management.appraisal.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ManagerReviewController {

	@RequestMapping("/managerAppraisal")
	public ModelAndView createManagerModel(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeID", request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		mv.setViewName("ManagerReview");
		return mv;
	}

	@RequestMapping("/employeeNamesWithID")
	public List<String> getEmployeeNamesWithID(@RequestBody String managerID, HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		return new ManagerReviewDataDAO().getEmployeeNamesWithID(managerID);
	}

	@RequestMapping("/getSelfAppraisalInfo")
	public List<Object> getSelfAppraisal(@RequestBody String employeeID, HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		return new ManagerReviewDataDAO().getSelfReviewInfo(employeeID);
	}

	@RequestMapping(value = "/submitReviewData", method = RequestMethod.POST)
	public @ResponseBody List<Object> getAppraisalData(@RequestBody ManagerReviewData managerReviewData,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		return new ManagerReviewDataDAO().processInput(managerReviewData);
	}

}
