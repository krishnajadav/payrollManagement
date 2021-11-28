package com.asdc.payroll_management.LeaveManagement.BL;

import com.alibaba.fastjson.JSONObject;
import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveType;

import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface IEmployeeLeaves {

    List<LeaveRequest> getAllLeaves();

    public String getLeaveType(String leaveName);

    public boolean addEmployeeLeave(LeaveRequest newLeaveRequest);

    public HashMap<Boolean,LeaveRequest> validateLeaveRequest(JSONObject leaveRequest);

    public Date getEndDate(Date startdate, int days);

    public int getDurartion(Date startdate, Date endDate) throws ParseException;

    public boolean checkDateRange(LeaveRequest a, LeaveType b);


}
