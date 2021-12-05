package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReimbursementController {

	@RequestMapping("/addReimbursements")
	public ModelAndView addReimbursements() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddReimbursementsForm");
		return mv;
	}

	@RequestMapping("/viewMyReimbursements")
	public List<ReimbursementRequest> viewMyReimbursements(@RequestBody String EmployeeID) throws SQLException {
		ReimbursementConcreteFactory rcf = new ReimbursementConcreteFactory();
		return rcf.getAllReimbursements(EmployeeID);

	}

	@RequestMapping("/viewEmployeeReimbursements")
	public List<ReimbursementRequest> viewEmployeeReimbursements() {

		return null;

	}

	@RequestMapping("/hello")
	public String hello() {

		return "Hello";

	}

}
