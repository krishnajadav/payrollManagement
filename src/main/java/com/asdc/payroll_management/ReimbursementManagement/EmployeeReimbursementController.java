package com.asdc.payroll_management.ReimbursementManagement;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

@RestController
public class EmployeeReimbursementController {

	private List<ReimbursementRequest> reimbursementRequestList;

	@RequestMapping("/addReimbursements")
	public ModelAndView addReimbursements() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddReimbursementsForm");
		return mv;
	}

	@RequestMapping(value = "/viewMyReimbursements")
	public List<ReimbursementRequest> viewMyReimbursements(Model model) {
		IEmployeeReimbursements reimbursementManager = new EmployeeReimbursementsDAO("1225");
		System.out.println("Done");
		reimbursementRequestList = reimbursementManager.getAllReimbursements();
		model.addAttribute("ReimbursementRequestList", reimbursementRequestList);
		if (reimbursementRequestList == null)
			System.out.println("Null");
		return reimbursementRequestList;
	}

	@RequestMapping(value = "/getEmployeeReimbursements", method = RequestMethod.POST)
	public @ResponseBody ReimbursementRequest addEmployeeReimbursements(
			@RequestBody ReimbursementRequest employeeReimbursementData) {
		employeeReimbursementData.setRR_EmployeeID("1225");
		IEmployeeReimbursements reimbursementManager = new EmployeeReimbursementsDAO(
				employeeReimbursementData.getRR_EmployeeID());
		ReimbursementRequest validReimbursementRequest = reimbursementManager
				.validateReimbursementRequest(employeeReimbursementData);
		if (validReimbursementRequest != null && validReimbursementRequest.getError() == null) {
			reimbursementManager.addEmployeeReimbursement(validReimbursementRequest);
			System.out.println("Insertion Successfull");
		} else {
			System.out.println("Insertion Failed");
		}
		return validReimbursementRequest;
	}

}
