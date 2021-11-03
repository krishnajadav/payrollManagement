package com.asdc.payroll_management.LeaveManagement.BL;

import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveType;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IEmployeeLeaves {

    List<LeaveRequest> getAllLeaves() throws SQLException;

    public List<LeaveType> getAllLeaveTypes() throws SQLException;

    public boolean addEmployeeLeave(String ID, int Duration, int type, Date startDate, Boolean isAccepted, Date Leave_End_Date) throws ParseException, SQLException;

    public Date getEndDate(Date startdate, int days);

    public int getDurartion(Date startdate, Date endDate) throws ParseException;

    public boolean checkDateRange(LeaveRequest a, LeaveType b);


}
