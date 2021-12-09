package com.asdc.payroll_management.appraisal.self;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

@RestController
public class AppraisalController {

	@RequestMapping("/appraisal")
	public ModelAndView getAppraisalModel(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeID", request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		mv.addObject("employeeName", EmployeeCache.getInstance()
				.getEmployee(request.getSession().getAttribute("userInfo").toString().split("#")[0]).getEmployeeName());
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
