package com.asdc.payroll_management.LeaveManagement;

import javax.servlet.http.HttpServletRequest;

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
    public @ResponseBody LeaveRequest addEmployeeLeaves(@RequestBody LeaveRequest employeeLeaveData,HttpServletRequest request) {

      //  System.out.println(employeeLeaveData.toString());
       // System.out.println(employeeLeaveData.toJSONString());
      //  String[] userInfo=request.getSession().getAttribute("userInfo").toString().split("#");
        IEmployeeLeaves leavesManager = new EmployeeLeavesDOA(employeeLeaveData.getLR_EmployeeID());
       // employeeLeaveData.setLR_EmployeeID(userInfo[0]);
        LeaveRequest validLeaveRequest = leavesManager.validateLeaveRequest(employeeLeaveData);
        if(validLeaveRequest!=null && validLeaveRequest.getError()==null){
            leavesManager.addEmployeeLeave(validLeaveRequest);
            System.out.println("Insert Successful");
        }else{
            System.out.println("Invalid Leave");
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