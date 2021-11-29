package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.LeaveRequestCache;
import com.asdc.payroll_management.DataBaseCache.LeavesCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import com.asdc.payroll_management.DataBaseCache.Leaves;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EmployeeLeavesDOA implements IEmployeeLeaves {
    private LeaveRequest leaveRequest=null;
    private Employee employee=null;

    public EmployeeLeavesDOA(String empID) {
        EmployeeCache employeeCache=EmployeeCache.getInstance();
        employee= employeeCache.getOne(empID);
    }

    @Override
    public List<LeaveRequest> getAllLeaves(){
        List<LeaveRequest> leaveRequests= new ArrayList<>();
        LeaveRequestCache leaveRequestCache=LeaveRequestCache.getInstance();
        HashMap<String, LeaveRequest> allLeaves =leaveRequestCache.get();

        for (Map.Entry mapElement : allLeaves.entrySet()) {
            LeaveRequest leaveRequest = (LeaveRequest) mapElement.getValue();

            if(leaveRequest.getLR_EmployeeID().equalsIgnoreCase(employee.getEmployee_ID()) && leaveRequest.getIsAccepted() == null){
                leaveRequests.add(leaveRequest);
                //System.out.println(leaveRequest);
            }
        }
        return leaveRequests;
    }

    @Override
    public Leaves getLeaveType(String leaveName){
        Leaves leaveType = null;
        LeavesCache leavesCache= LeavesCache.getInstance();
        HashMap<String,Leaves> leavesHashMap = leavesCache.get();
        for (Map.Entry mapElement : leavesHashMap.entrySet()) {
            leaveType = (Leaves) mapElement.getValue();
            if(leaveType.getLeaves_Name().equalsIgnoreCase(leaveName)){
                break;
            }
        }
        return leaveType;
        }



    @Override
    public boolean addEmployeeLeave(LeaveRequest leaveRequest){
        LeaveRequestCache leaveRequestCache = LeaveRequestCache.getInstance();
        Boolean insertStatus=leaveRequestCache.insert(leaveRequest);
        return insertStatus;
    }

    @Override
    public LeaveRequest validateLeaveRequest(LeaveRequest leaveRequest){
        //String LR_ID =leaveRequest.getString("EmployeeID");
        String LR_EmployeeID = leaveRequest.getLR_EmployeeID();

        Integer LR_Duration = leaveRequest.getLR_Duration().equalsIgnoreCase("")?0:Integer.parseInt(leaveRequest.getLR_Duration());

        Leaves LR_Type = getLeaveType(leaveRequest.getLR_Type());
//        String isAccepted = leaveRequest.getString("EmployeeID");
        Date Leave_Request_Date =  new Date(Long.parseLong(leaveRequest.getLeave_Request_Date()));
        Date Leave_End_Date =  leaveRequest.getLeave_End_Date()==null ? null: new Date(Long.parseLong(leaveRequest.getLeave_End_Date()));

        String ErrorMessage="";
        Boolean validResponse=true;
        if(LR_Type == null){
            validResponse=false;
            ErrorMessage=ErrorMessage+" Leave Type Invalid";
        }else if(!this.checkEndDateandDurartion(Leave_End_Date, LR_Duration)){
            validResponse=false;
            ErrorMessage=ErrorMessage+" End Date or Duration should be populated";
        }else if(!(this.checkDateRange(Integer.parseInt(LR_Type.getLeaves_DuartionLimit()),LR_Duration))){
            validResponse=false;
            ErrorMessage=ErrorMessage+" Requested leaves extend the duration limit of the leave type";
        }else if(this.checkStartDateAndEndDate(Leave_Request_Date,Leave_End_Date)){
            validResponse=false;
            ErrorMessage=ErrorMessage+" Start Date should be before End Date";
        }else if(LR_Duration <=0){
            LR_Duration =(this.getDurartion(Leave_Request_Date,Leave_End_Date));
        }else if(Leave_End_Date== null){
            Leave_End_Date=this.getEndDate(Leave_Request_Date,LR_Duration);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(Leave_Request_Date);
        String endDate = dateFormat.format(Leave_End_Date);

        System.out.println(LR_EmployeeID+" "+(LR_Duration).toString()+" "+LR_Type.getLeaves_ID()+" "+strDate+" "+endDate);

        LeaveRequest newLeaveRequest =null;
        if(validResponse){
             newLeaveRequest = new LeaveRequest(null,LR_EmployeeID,(LR_Duration).toString(),LR_Type.getLeaves_ID(),null,strDate,endDate);
        }

        return newLeaveRequest;
    }


    @Override
    public boolean checkDateRange(int acceptedRange, int givenDuration) {

        if(acceptedRange>=givenDuration){
            return true;
        }else{
            return false;
        }

    }


    @Override
    public Date getEndDate(Date startdate, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(startdate);
        cal.add(Calendar.DATE,days);
        return (Date) cal.getTime();
    }

    @Override
    public int getDurartion(Date startdate, Date endDate){
        try {
            long DiffInMilies = Math.abs(endDate.getTime() - startdate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(DiffInMilies, TimeUnit.MILLISECONDS);

            return (int) diffInDays;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean checkEndDateandDurartion(Date enddate, int durartion) {
        if(durartion <=0 && enddate == null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public boolean checkStartDateAndEndDate(Date startdate, Date endDate) {
        if (startdate == null) {
            return true;
        }else if(endDate == null){
            return false;
        }
        if(startdate.compareTo(endDate)>0){
            return true;
        }else{
            return false;
        }

    }

}
