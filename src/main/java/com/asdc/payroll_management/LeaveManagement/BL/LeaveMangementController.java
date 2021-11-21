package com.asdc.payroll_management.LeaveManagement.BL;

import com.alibaba.fastjson.JSONObject;
import com.asdc.payroll_management.LeaveManagement.Database;
import com.asdc.payroll_management.LeaveManagement.DatabaseDML;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.juli.logging.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.jfunc.json.impl.JSONArray;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController

public class LeaveMangementController {

    private  List<LeaveRequest> leaveRequestList;
//    public LeaveMangementController(DatabaseDML databaseDML) {
//        this.databaseDML=databaseDML;
//    }

    @RequestMapping("/AddLeaves")
    public ModelAndView addLeaves() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("AddLeavesForm");
        return mv;
    }



    @RequestMapping(value = "/getEmployeeLeaves", method = RequestMethod.POST)
    public @ResponseBody LeaveRequest employeeCodeGenerator(@RequestBody JSONObject employeeLeaveData) throws SQLException, ParseException {

        System.out.println(employeeLeaveData.toJSONString());
        String leavetype =employeeLeaveData.getString("LeaveTypeID");
        int leaveTypeID=0;
        if(leavetype.equalsIgnoreCase("Sick Leaves")){
             leaveTypeID=1;
        }else if(leavetype.equalsIgnoreCase("Casual Leaves")){
              leaveTypeID=2;
        }else if(leavetype.equalsIgnoreCase("Annual Leaves")){
              leaveTypeID=3;
        }else if(leavetype.equalsIgnoreCase("Maternity Leaves")){
              leaveTypeID=4;
        }else if(leavetype.equalsIgnoreCase("Paternity Leaves")){
              leaveTypeID=5;
        }

        int leaveDuration = 0;
        if(employeeLeaveData.getString("LeaveDuration").equalsIgnoreCase("0") || employeeLeaveData.getString("LeaveDuration").equalsIgnoreCase("")){
            leaveDuration=0;
        }else{
            leaveDuration=employeeLeaveData.getInteger("LeaveDuration");
        }

        System.out.println(leaveDuration + " - "+leaveTypeID);

        LeaveRequest newLeaveRequest = new LeaveRequest(employeeLeaveData.getString("EmployeeID"),
                leaveDuration,leaveTypeID,
                employeeLeaveData.getDate("LeaveStartdate"),employeeLeaveData.getBoolean("isAccepted"),
                employeeLeaveData.getDate("LeaveEndDate"));

        IEmployeeLeaves leaveManager = new EmployeeleaveConcrete(new DatabaseDML(new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT")));
        boolean insertionStatus = leaveManager.addEmployeeLeave(newLeaveRequest);
        if(insertionStatus){
            System.out.println("Inserted Successfully");
        } else {
            System.err.println("Insertion Failed");
        }
        return newLeaveRequest ;
    }


//    @GetMapping(value = "/viewMyLeaves")
//    public String  viewMyLeaves(@RequestParam(name = "empcode") String empcode, Model model) throws SQLException {
//
//        IEmployeeLeaves leaveManager = new EmployeeleaveConcrete(new LeaveEmployee(empcode,null,null),
//                                                                new DatabaseDML(new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT")));
//        leaveRequestList = leaveManager.getAllLeaves();
//        model.addAttribute("LeaveRequestlist",leaveRequestList);
//
//        return "All Leave Request for ID: "+empcode;
//    }


    }
