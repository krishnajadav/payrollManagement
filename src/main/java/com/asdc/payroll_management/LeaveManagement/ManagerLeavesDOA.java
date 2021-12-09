package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerLeavesDOA implements IManagerLeaves{

    private Employee employee=null;

    public ManagerLeavesDOA(String empID){
        EmployeeCache employeeCache = EmployeeCache.getInstance();
        employee=employeeCache.getEmployee(empID);
    }

    @Override
    public List<Employee> getAllStaff() {
        EmployeeCache employeeCache = EmployeeCache.getInstance();
        HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
        List<Employee> employees = new ArrayList<>();
        for (Map.Entry mapElement : employeeHashMap.entrySet()) {
            Employee employeeTemp = (Employee) mapElement.getValue();
            if (employeeTemp.getManagerID().equalsIgnoreCase(employee.getEmployeeID())) {
                employees.add(employeeTemp);
            }
        }
        return employees;
    }

    @Override
    public Boolean acceptLeave(String lrID){
        LeaveRequestCache leaveRequestCache = LeaveRequestCache.getInstance();
        LeaveRequest leaveRequest = leaveRequestCache.getOneLeave(lrID);
        Boolean updateStatus= leaveRequestCache.updateLeaveTrue(leaveRequest);
        return updateStatus;
        }


        @Override
        public Boolean rejectLeave(String lrID){
            LeaveRequestCache leaveRequestCache = LeaveRequestCache.getInstance();
            LeaveRequest leaveRequest = leaveRequestCache.getOneLeave(lrID);
            Boolean updateStatus= leaveRequestCache.updateLeaveFalse(leaveRequest);
            return updateStatus;
        }

}
