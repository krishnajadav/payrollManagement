package com.asdc.payroll_management.LeaveManagement.BL;

import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerLeavesConcrete extends EmployeeleaveConcrete{



    public ManagerLeavesConcrete(LeaveEmployee emp) {
        super(emp);
    }

    public List<LeaveRequest> getSpecificEmployeeLeaves(LeaveEmployee emp) throws SQLException {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String query = "select * from `Leave_Request` where LR_EmployeeID=\""+emp.getEmployeeID();
        this.getConnection();
        ResultSet allLeaves= dbConn.ViewqueryResultset(query);
        while (allLeaves.next()) {
            String EmployeeID = allLeaves.getString("LR_EmployeeID");
            int LR_Duration = allLeaves.getInt("LR_Duration");
            int LR_Type = allLeaves.getInt("LR_Type");
            Boolean isAccepted = allLeaves.getBoolean("isAccepted");
            Date Leave_Request_Date = allLeaves.getDate("Leave_Request_Date");
            Date Leave_End_Date = allLeaves.getDate("Leave_End_Date");
            LeaveRequest obj = this.createLeaveRequest(LR_Duration,LR_Type,Leave_Request_Date,isAccepted,Leave_End_Date);
            leaveRequests.add(obj);
        }

        return leaveRequests;
    }

    public boolean updateLeaveRequest(LeaveEmployee emp, LeaveRequest lr,Boolean UpdatedAccepted ){
        if(emp.getManagerID()!=curEmp.getEmployeeID()){
        return false;
        }
        lr.setAccepted(UpdatedAccepted);

        return true;
    }


}
