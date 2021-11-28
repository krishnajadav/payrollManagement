package com.asdc.payroll_management.LeaveManagement.BL;

import com.asdc.payroll_management.LeaveManagement.Database;
import com.asdc.payroll_management.LeaveManagement.DatabaseDML;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
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

    @GetMapping(value = "/viewEmployeeLeaves/{empcode}")
    public List<LeaveRequest> viewMyLeaves(@PathVariable String empcode, Model model) throws SQLException {

        IEmployeeLeaves leaveManager = new ManagerLeavesConcrete(new LeaveEmployee(empcode,null,null),
                new DatabaseDML(new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT")));
        leaveRequestList = ((ManagerLeavesConcrete) leaveManager).getSpecificEmployeeLeaves(empcode);
//        for(LeaveRequest lr : leaveRequestList){
//            System.out.println(lr.getEmployeeID());
//        }
        model.addAttribute("LeaveRequestlist",leaveRequestList);
        return leaveRequestList;
    }

    @RequestMapping(value = "/acceptLeaveRequest/{lrID}")
    public boolean acceptEmployeeLeaves(@PathVariable String lrID){

        System.out.println(lrID);
        IEmployeeLeaves leaveManager = new ManagerLeavesConcrete(new LeaveEmployee(null,null,null),
                new DatabaseDML(new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT")));
    Boolean updateRequest=((ManagerLeavesConcrete) leaveManager).acceptLeaveRequest(lrID,1);
        System.out.println(updateRequest);
        return updateRequest;
    }

    @RequestMapping(value = "/denyLeaveRequest/{lrID}")
    public boolean denyEmployeeLeaves(@PathVariable String lrID){
        System.out.println(lrID);
        IEmployeeLeaves leaveManager = new ManagerLeavesConcrete(new LeaveEmployee(null,null,null),
                new DatabaseDML(new Database("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT")));
        Boolean updateRequest=((ManagerLeavesConcrete) leaveManager).acceptLeaveRequest(lrID,0);
        System.out.println(updateRequest);
        return updateRequest;
    }


}
