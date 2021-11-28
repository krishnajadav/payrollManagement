package com.asdc.payroll_management.LeaveManagement.BL;

import com.asdc.payroll_management.LeaveManagement.DatabaseDML;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerLeavesConcrete extends EmployeeleaveConcrete{



    public ManagerLeavesConcrete(LeaveEmployee emp, DatabaseDML dbConn) {
        super(emp,dbConn);
    }

    public List<LeaveRequest> getSpecificEmployeeLeaves(String emp_id) throws SQLException {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String query = "select * from `Leave_Request` where LR_EmployeeID=\""+emp_id+"\" and isAccepted is null;";
        this.getConnection();
        ResultSet allLeaves= dbConn.ViewqueryResultset(query);
        while (allLeaves.next()) {
            String LR_ID = Integer.toString(allLeaves.getInt("LR_ID"));
            String EmployeeID = allLeaves.getString("LR_EmployeeID");
            int LR_Duration = allLeaves.getInt("LR_Duration");
            int LR_Type = allLeaves.getInt("LR_Type");
            String isAccepted = allLeaves.getString("isAccepted");
            Date Leave_Request_Date = allLeaves.getDate("Leave_Request_Date");
            Date Leave_End_Date = allLeaves.getDate("Leave_End_Date");
            LeaveRequest obj = this.createLeaveRequest(LR_Duration,LR_Type,Leave_Request_Date,isAccepted,Leave_End_Date);
            obj.setLR_ID(LR_ID);
            leaveRequests.add(obj);
        }

        return leaveRequests;
    }

    public boolean acceptLeaveRequest(String lrID,int boolVal){
        String query="update `Leave_Request` set isAccepted="+boolVal+" where LR_ID=\""+lrID+"\";";
        Boolean updateStatus=dbConn.updateQuery(query);
        return updateStatus;
    }


}
