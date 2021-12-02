package com.asdc.payroll_management.ReimbursementManagement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReimbursementController {
	
	@RequestMapping("/AddReimbursements")
	public ModelAndView addReimbursements() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddReimbursementsForm");
		return mv;
	}
	
	
	
	
}
