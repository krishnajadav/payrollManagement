package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController

public class ManagerLeaveController {

    private  List<LeaveRequest> leaveRequestList;

    @RequestMapping("/ManageLeaves")
    public ModelAndView addLeaves() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ManageLeavesForm");
        return mv;
    }

    @RequestMapping(value = "/getAllStaff")
    public List<Employee> viewMyLeaves(@RequestBody String empcode) {
        ManagerLeavesDOA managerLeavesDOA = new ManagerLeavesDOA(empcode);
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
