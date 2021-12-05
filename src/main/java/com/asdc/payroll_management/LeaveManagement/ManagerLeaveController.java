package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

public class ManagerLeaveController {

    private  List<LeaveRequest> leaveRequestList;

    @RequestMapping("/ManageLeaves")
    public ModelAndView addLeaves(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if(request.getSession().getAttribute("userInfo")!=null)
        {
            mv.setViewName("ManageLeavesForm");
            return mv;
        }
        else
        {
            return new ModelAndView("redirect:/LoginSignup");
        }
    }

    @RequestMapping(value = "/getAllStaff")
    public List<Employee> viewMyLeaves(HttpServletRequest request) {

        String[] userInfo=request.getSession().getAttribute("userInfo").toString().split("#");
        ManagerLeavesDOA managerLeavesDOA = new ManagerLeavesDOA(userInfo[0]);
        List<Employee> allStaffEmployees = managerLeavesDOA.getAllStaff();
        return allStaffEmployees;
    }

    @RequestMapping(value = "/viewStaffLeaves")
    public List<LeaveRequest> viewMyLeaves(@RequestBody String empcode, Model model) {
        EmployeeLeavesDOA employeeLeavesDOA = new EmployeeLeavesDOA(empcode);
        leaveRequestList=employeeLeavesDOA.getAllLeaves();
        model.addAttribute("LeaveRequestlist",leaveRequestList);
        return leaveRequestList;
    }

    @RequestMapping(value = "/acceptLeaveRequest")
    public boolean acceptEmployeeLeaves(@RequestBody String lrId){

        System.out.println(lrId);

        ManagerLeavesDOA managerLeavesDOA =new ManagerLeavesDOA(null);

        Boolean updateRequest=managerLeavesDOA.acceptLeave(lrId);
        System.out.println(updateRequest);
        return updateRequest;
    }

    @RequestMapping(value = "/denyLeaveRequest")
    public boolean denyEmployeeLeaves(@RequestBody String lrID){
        System.out.println(lrID);
        ManagerLeavesDOA managerLeavesDOA =new ManagerLeavesDOA(null);
        Boolean updateRequest=managerLeavesDOA.rejectLeave(lrID);
        System.out.println(updateRequest);
        return updateRequest;
    }


}
