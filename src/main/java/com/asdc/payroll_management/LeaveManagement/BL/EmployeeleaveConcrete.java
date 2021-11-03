package com.asdc.payroll_management.LeaveManagement.BL;

import com.asdc.payroll_management.LeaveManagement.DatabaseDML;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveType;
import java.util.Date;

// import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmployeeleaveConcrete implements IEmployeeLeaves{

    public LeaveEmployee curEmp;
    public List<LeaveType> leaveTypes;
    public DatabaseDML dbConn;

    public EmployeeleaveConcrete(LeaveEmployee emp){
        curEmp=emp;
    }

    public DatabaseDML getConnection(){
        if (dbConn==null){
            dbConn=new DatabaseDML("db-5308.cs.dal.ca","3306","aiJ9Eidoo1kieyej","CSCI5308_17_DEVINT_USER","CSCI5308_17_DEVINT");
        }
        return dbConn;
    }

    public LeaveRequest createLeaveRequest( int Duration, int type, Date startDate, Boolean isAccepted, Date Leave_End_Date){

        return new LeaveRequest( curEmp.getEmployeeID(),  Duration,  type,  startDate,  isAccepted,  Leave_End_Date);
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() throws SQLException {
        List<LeaveType> lt = new ArrayList<>();
        String query = "select * from `Leaves` where Leaves";
        this.getConnection();
        ResultSet allLeavesTypes = dbConn.ViewqueryResultset(query);
        while (allLeavesTypes.next()) {
            int id = allLeavesTypes.getInt("allLeavesTypes");
            String Leaves_Name = allLeavesTypes.getString("Leaves_Name");
            int Leaves_DuartionLimit = allLeavesTypes.getInt("Leaves_DuartionLimit");

            LeaveType obj = new LeaveType(id, Leaves_Name,Leaves_DuartionLimit );
            lt.add(obj);
        }
        return lt;
    }

        public List<LeaveRequest> getAllLeaves() throws SQLException {
            List<LeaveRequest> leaveRequests = new ArrayList<>();
            String query = "select * from `Leave_Request` where LR_EmployeeID=\""+curEmp.getEmployeeID();
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



    @Override
    public boolean addEmployeeLeave(String ID, int Duration, int type, Date startDate, Boolean isAccepted, Date Leave_End_Date) throws ParseException, SQLException {
        this.getConnection();
        LeaveRequest newLeaverequest = this.createLeaveRequest(Duration,  type,  startDate,  isAccepted,  Leave_End_Date);
        leaveTypes = this.getAllLeaveTypes();
        LeaveType requestedType = null;
        for(LeaveType tmp : leaveTypes){
            if(tmp.getId()==type){
                requestedType=tmp;
                break;
            }
        }


        if(this.checkEndDateandDurartion(Leave_End_Date,Duration)==false){
            return false;
        }else if(Duration <=0){
            newLeaverequest.setLeaveDuration(this.getDurartion(startDate,Leave_End_Date));
        }else if(Leave_End_Date == null){
            newLeaverequest.setLeaveEndDate(this.getEndDate(startDate,Duration));
        }

        if(this.checkDateRange(newLeaverequest,requestedType)==false){
            return false;
        }
        return dbConn.InsertLRResultset(newLeaverequest);
    }

    @Override
    public Date getEndDate(Date startdate, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(startdate);
        cal.add(Calendar.DATE,days);

        return (Date) cal.getTime();
    }

    @Override
    public int getDurartion(Date startdate, Date endDate) throws ParseException {

        long DiffInMilies = Math.abs(endDate.getTime() - startdate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(DiffInMilies,TimeUnit.MILLISECONDS);

        return (int)diffInDays;
    }

    @Override
    public boolean checkDateRange(LeaveRequest a, LeaveType b) {

        int acceptedRange=b.getLeaveDuration();
        int givenDuration=a.getLeaveDuration();
        if(acceptedRange>=givenDuration){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkEndDateandDurartion(Date enddate, int durartion) {
        if(durartion <=0 && enddate == null){
            return false;
        }else{
            return true;
        }

    }

}
