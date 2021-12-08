package com.asdc.payroll_management.ReimbursementManagement;

import javax.servlet.http.HttpServletRequest;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController

public class ReimbursementController {

	private List<ReimbursementRequest> reimbursementRequestList;

	@RequestMapping("/AddReimbursement")
	public ModelAndView addReimbursements(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddReimbursementForm");
		return mv;
	}

	@RequestMapping(value = "/getReimbursementRequest", method = RequestMethod.POST)
	public @ResponseBody ReimbursementRequest addEmployeeReimbursementRequest(@RequestBody String frontEndData) throws ParseException {
		String[] data = frontEndData.substring(1, frontEndData.length() - 1).split(",");
		ReimbursementRequest reimbursementRequest = new ReimbursementRequest(
				data[0].split(":")[1].substring(1, data[0].split(":")[1].length() - 1),
				data[1].split(":")[1].substring(1, data[1].split(":")[1].length() - 1),
				data[2].split(":")[1].substring(1, data[2].split(":")[1].length() - 1),
				data[3].split(":")[1].substring(1, data[3].split(":")[1].length() - 1));
		reimbursementRequest.setRR_EmployeeID("1225");
		reimbursementRequest.setIsAccepted("Pending");
		IEmployeeReimbursement reimbursementManager = new EmployeeReimbursementDOA("1225");
		ReimbursementRequest validatedReimbursementRequest = reimbursementManager
				.validateReimbursementRequest(reimbursementRequest);
		if (validatedReimbursementRequest != null && validatedReimbursementRequest.getError() == null) {
			reimbursementManager.addReimbursementRequest(validatedReimbursementRequest);
		}
		return validatedReimbursementRequest;

	}

	@RequestMapping(value = "/viewMyReimbursements")
	public List<ReimbursementRequest> viewMyReimbursements(Model model, HttpServletRequest request)
			throws SQLException {
		IEmployeeReimbursement reimbursementManager = new EmployeeReimbursementDOA("1225");

		reimbursementRequestList = reimbursementManager.getAllReimbursements();
		model.addAttribute("LeaveRequestlist", reimbursementRequestList);

		return reimbursementRequestList;
	}

	@RequestMapping("/Manager/ReimbursementView")
	public ModelAndView viewManagerReimbursements(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ManagerReimbursementsForm");
		return mv;
	}

	@RequestMapping(value = "/Manager/getAllStaff")
	public List<Employee> getAllStaff(HttpServletRequest request) {
		ManagerReimbursementDOA managerLeavesDOA = new ManagerReimbursementDOA("1226");
		List<Employee> allStaffEmployees = managerLeavesDOA.getAllEmployees();
		return allStaffEmployees;
	}

	@RequestMapping(value = "/Manager/viewAllStaffReimbursements")
	public List<ReimbursementRequest> viewAllStaffReimbursements(@RequestBody String empcode, Model model) {
		ManagerReimbursementDOA managerReimbursementDOA = new ManagerReimbursementDOA(empcode);
		reimbursementRequestList = managerReimbursementDOA.getAllStaffReimbursements();
		model.addAttribute("LeaveRequestlist", reimbursementRequestList);
		return reimbursementRequestList;
	}

	@RequestMapping(value = "/Manager/acceptReimbursementRequest")
	public boolean acceptEmployeeReimbursements(@RequestBody String reimbersementID) {

		ManagerReimbursementDOA managerReimbersementsDOA = new ManagerReimbursementDOA(null);

		Boolean updateRequest = managerReimbersementsDOA.acceptReimbursement(reimbersementID);
		return updateRequest;
	}

	@RequestMapping(value = "/Manager/rejectReimbursementRequest")
	public boolean denyEmployeeReimbursements(@RequestBody String reimbersementID) {
		ManagerReimbursementDOA managerReimbersementsDOA = new ManagerReimbursementDOA(null);
		Boolean updateRequest = managerReimbersementsDOA.rejectReimbursement(reimbersementID);
		return updateRequest;
	}

}
