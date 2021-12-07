package com.asdc.payroll_management.appraisal.final_rating;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
	
@RestController
public class RatingController {
	
	@RequestMapping("/rating")
	public ModelAndView getRating() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("rating");
		return mv;
	}
	
	@RequestMapping("/getRatingInfo")
	public List<Object> getRatingInfo(@RequestBody String employeeID) {
		return new FinalRatingDAOImpl().getFinalRatingData(employeeID);
	}
	
}
