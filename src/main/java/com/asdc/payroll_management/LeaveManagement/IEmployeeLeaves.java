package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import com.asdc.payroll_management.DataBaseCache.Leaves;

import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface IEmployeeLeaves {

    List<LeaveRequest> getAllLeaves();

    public Leaves getLeaveType(String leaveName);

    public boolean addEmployeeLeave(LeaveRequest newLeaveRequest);

    public LeaveRequest validateLeaveRequest(LeaveRequest leaveRequest);

    public Date getEndDate(Date startdate, int days);

    public int getDurartion(Date startdate, Date endDate) throws ParseException;

    public boolean checkDateRange(int acceptedRange, int givenDuration);

    public boolean checkEndDateandDurartion(Date enddate, int durartion);

    public boolean checkStartDateAndEndDate(Date startdate, Date endDate);


}
