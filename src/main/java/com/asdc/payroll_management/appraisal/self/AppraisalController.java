package com.asdc.payroll_management.appraisal.self;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
	
@RestController
public class AppraisalController {
	
	@RequestMapping("/appraisal")
	public ModelAndView getAppraisalModel() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SelfReview");
		return mv;
	}
	
	@RequestMapping("/getPeerInfo")
	public List<String> getPeerInfo(@RequestBody String employeeID) {
		return new AppraisalDataDAO().getPeerInfo(employeeID);
	}
	
	@RequestMapping(value = "/submitAppraisalData", method = RequestMethod.POST)
	public @ResponseBody List<Object> getSelfReviewData(@RequestBody SelfReviewData reviewData) {
		return new AppraisalDataDAO().processInput(reviewData);
	}
}
