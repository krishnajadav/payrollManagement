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
	public ModelAndView addReimbursement(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddReimbursementForm");
		return mv;
	}

	@RequestMapping(value = "/getReimbursementRequest", method = RequestMethod.POST)
	public @ResponseBody ReimbursementRequest addEmployeeReimbursementRequest(@RequestBody String frontEndData,
			HttpServletRequest request) throws ParseException {
		if (request.getSession().getAttribute("userInfo") == null) {
			return null;
		}
		String[] data = frontEndData.substring(1, frontEndData.length() - 1).split(",");
		ReimbursementRequest reimbursementRequest = new ReimbursementRequest(
				data[0].split(":")[1].substring(1, data[0].split(":")[1].length() - 1),
				data[1].split(":")[1].substring(1, data[1].split(":")[1].length() - 1),
				data[2].split(":")[1].substring(1, data[2].split(":")[1].length() - 1),
				data[3].split(":")[1].substring(1, data[3].split(":")[1].length() - 1));
		reimbursementRequest.setRrEmployeeid(request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		reimbursementRequest.setIsAccepted("Pending");
		IEmployeeReimbursement reimbursementManager = new EmployeeReimbursementDOA(request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		ReimbursementRequest validatedReimbursementRequest = reimbursementManager
				.validateReimbursementRequest(reimbursementRequest);
		if (validatedReimbursementRequest != null && validatedReimbursementRequest.getErrorMsg() == null) {
			reimbursementManager.addReimbursementRequest(validatedReimbursementRequest);
		}
		return validatedReimbursementRequest;

	}

	@RequestMapping(value = "/viewMyReimbursements")
	public List<ReimbursementRequest> viewMyReimbursements(Model model, HttpServletRequest request)
			throws SQLException {
		if (request.getSession().getAttribute("userInfo") == null) {
			return null;
		}
		IEmployeeReimbursement reimbursementManager = new EmployeeReimbursementDOA(request.getSession().getAttribute("userInfo").toString().split("#")[0]);

		reimbursementRequestList = reimbursementManager.getAllReimbursements();
		for (ReimbursementRequest re : reimbursementRequestList) {
			System.out.println(re.getRRTypeID());
		}
		model.addAttribute("LeaveRequestlist", reimbursementRequestList);
		return reimbursementRequestList;
	}

	@RequestMapping("/ManagerReimbursementView")
	public ModelAndView viewManagerReimbursements(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") == null) {
			return new ModelAndView("redirect:/LoginSignup");
		}
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ManagerReimbursementsForm");
		return mv;
	}

	@RequestMapping(value = "/ManagerGetAllStaff")
	public List<Employee> getAllStaff(HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		ManagerReimbursementDOA managerLeavesDOA = new ManagerReimbursementDOA(request.getSession().getAttribute("userInfo").toString().split("#")[0]);
		List<Employee> allStaffEmployees = managerLeavesDOA.getAllEmployees();
		return allStaffEmployees;
	}

	@RequestMapping(value = "/ManagerViewAllStaffReimbursements")
	public List<ReimbursementRequest> viewAllStaffReimbursements(@RequestBody String empcode, Model model,
			HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		ManagerReimbursementDOA managerReimbursementDOA = new ManagerReimbursementDOA(empcode);
		reimbursementRequestList = managerReimbursementDOA.getAllStaffReimbursements();
		model.addAttribute("LeaveRequestlist", reimbursementRequestList);
		return reimbursementRequestList;
	}

	@RequestMapping(value = "/ManagerAcceptReimbursementRequest")
	public Boolean acceptEmployeeReimbursement(@RequestBody String reimbersementID, HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}

		ManagerReimbursementDOA managerReimbersementsDOA = new ManagerReimbursementDOA(null);

		Boolean updateRequest = managerReimbersementsDOA.acceptReimbursement(reimbersementID);
		return updateRequest;
	}

	@RequestMapping(value = "/ManagerRejectReimbursementRequest")
	public Boolean denyEmployeeReimbursement(@RequestBody String reimbersementID, HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo").toString().split("#")[2].equals("user")) {
			return null;
		}
		ManagerReimbursementDOA managerReimbersementsDOA = new ManagerReimbursementDOA(null);
		Boolean updateRequest = managerReimbersementsDOA.rejectReimbursement(reimbersementID);
		return updateRequest;
	}

}
