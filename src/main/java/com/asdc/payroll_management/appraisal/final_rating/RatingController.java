package com.asdc.payroll_management.appraisal.final_rating;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RatingController {

	@RequestMapping("/rating")
	public ModelAndView getRating(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeID", request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		mv.setViewName("rating");
		return mv;
	}

	@RequestMapping("/getRatingInfo")
	public List<Object> getRatingInfo(@RequestBody String employeeID) {
		return new FinalRatingDAOImpl().getFinalRatingData(employeeID);
	}

}
