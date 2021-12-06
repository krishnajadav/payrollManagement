package com.asdc.payroll_management.ReimbursementManagement;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

@RestController
public class ManagerReimbursementController {

	private List<ReimbursementRequest> reimbursementRequestList;

	@RequestMapping("/manageReimbursements")
	public ModelAndView manageReimbursements() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ManagerReimbursementsForm");
		return mv;
	}

	@RequestMapping(value = "/getAllStaffReimbursements")
	public List<Employee> viewAllStaffReimbursements() {

		ManagerReimbursementsDAO managerReimbursementsDOA = new ManagerReimbursementsDAO("1255");
		List<Employee> allStaffEmployees = managerReimbursementsDOA.getAllStaff();
		return allStaffEmployees;
	}

	@RequestMapping(value = "/viewAllStaffReimbursements")
	public List<ReimbursementRequest> viewAllStaffReimbursements(@RequestBody String employeeID, Model model) {
		EmployeeReimbursementsDAO employeeReimbursementsDOA = new EmployeeReimbursementsDAO(employeeID);
		reimbursementRequestList = employeeReimbursementsDOA.getAllReimbursements();
		model.addAttribute("ReimbursementRequestList", reimbursementRequestList);
		return reimbursementRequestList;
	}

	@RequestMapping(value = "/acceptReimbursementRequest")
	public boolean acceptEmployeeReimbursements(@RequestBody String RR_ID) {
		ManagerReimbursementsDAO managerReimbursementsDOA = new ManagerReimbursementsDAO(null);
		Boolean updateRequest = managerReimbursementsDOA.acceptReimbursement(RR_ID);
		return updateRequest;
	}

	@RequestMapping(value = "/denyReimbursementRequest")
	public boolean denyEmployeeReimbursements(@RequestBody String RR_ID) {
		ManagerReimbursementsDAO managerReimbursementsDOA = new ManagerReimbursementsDAO(null);
		Boolean updateRequest = managerReimbursementsDOA.rejectReimbursement(RR_ID);
		return updateRequest;
	}

}
