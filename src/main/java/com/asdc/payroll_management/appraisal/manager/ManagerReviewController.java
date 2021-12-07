package com.asdc.payroll_management.appraisal.manager;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ManagerReviewController {

	@RequestMapping("/managerAppraisal")
	public ModelAndView createManagerModel() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ManagerReview");
		return mv;
	}

	@RequestMapping("/employeeNamesWithID")
	public List<String> getEmployeeNamesWithID(@RequestBody String managerID) {
		return new ManagerReviewDataDAO().getEmployeeNamesWithID(managerID);
	}

	@RequestMapping("/getSelfAppraisalInfo")
	public List<Object> getSelfAppraisal(@RequestBody String employeeID) {
		return new ManagerReviewDataDAO().getSelfReviewInfo(employeeID);
	}

	@RequestMapping(value = "/submitReviewData", method = RequestMethod.POST)
	public @ResponseBody List<Object> getAppraisalData(@RequestBody ManagerReviewData managerReviewData) {
		return new ManagerReviewDataDAO().processInput(managerReviewData);
	}

}
