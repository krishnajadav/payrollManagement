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
    public ModelAndView addLeaves(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        if(request.getSession().getAttribute("userInfo")!=null)
        {
            mv.setViewName("AddLeavesForm");
            return mv;
        }
        else
        {
            return new ModelAndView("redirect:/LoginSignup");
        }
    }

    @RequestMapping(value = "/getEmployeeLeaves", method = RequestMethod.POST)
    public @ResponseBody LeaveRequest addEmployeeLeaves(@RequestBody LeaveRequest employeeLeaveData,HttpServletRequest request) {

       String[] userInfo=request.getSession().getAttribute("userInfo").toString().split("#");
       employeeLeaveData.setLrEmployeeid(userInfo[0]);
        IEmployeeLeaves leavesManager = new EmployeeLeavesDOA(employeeLeaveData.getLrEmployeeid());
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
    public List<LeaveRequest>  viewMyLeaves( Model model,HttpServletRequest request) throws SQLException {

        String[] userInfo=request.getSession().getAttribute("userInfo").toString().split("#");
        IEmployeeLeaves leavesManager = new EmployeeLeavesDOA(userInfo[0]);

        leaveRequestList = leavesManager.getAllLeaves();
        model.addAttribute("LeaveRequestlist",leaveRequestList);

        return leaveRequestList;
    }


    }
