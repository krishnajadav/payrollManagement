package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController

public class LeaveMangementController {

    private  List<LeaveRequest> leaveRequestList;

    @RequestMapping("/AddLeaves")
    public ModelAndView addLeaves() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddLeavesForm");
        return mv;
    }

    @RequestMapping(value = "/getEmployeeLeaves", method = RequestMethod.POST)
    public @ResponseBody LeaveRequest addEmployeeLeaves(@RequestBody LeaveRequest employeeLeaveData) {

      //  System.out.println(employeeLeaveData.toString());
       // System.out.println(employeeLeaveData.toJSONString());
        IEmployeeLeaves leavesManager = new EmployeeLeavesDOA(employeeLeaveData.getLR_EmployeeID());
        LeaveRequest validLeaveRequest = leavesManager.validateLeaveRequest(employeeLeaveData);
        if(validLeaveRequest!=null){
            leavesManager.addEmployeeLeave(validLeaveRequest);
            System.out.println("Insert Successful");
        }else{
            System.err.println("Invalid Leave");
        }
        return validLeaveRequest;


    }


    @RequestMapping(value = "/viewMyLeaves")
    public List<LeaveRequest>  viewMyLeaves(@RequestBody String empcode, Model model) throws SQLException {

        IEmployeeLeaves leavesManager = new EmployeeLeavesDOA(empcode);

        leaveRequestList = leavesManager.getAllLeaves();
        model.addAttribute("LeaveRequestlist",leaveRequestList);

        return leaveRequestList;
    }


    }
