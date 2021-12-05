package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class LeaveRequestCache {
    private static HashMap<String, LeaveRequest> modelLeaveRequest = new HashMap<>();
    private static LeaveRequestCache leaveRequestCache=null;

    public static LeaveRequestCache getInstance(){
        if(leaveRequestCache==null){
            leaveRequestCache=new LeaveRequestCache();
        }
        return leaveRequestCache;
    }

    private LeaveRequestCache()  {
        load();
    }


    private void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_LEAVE_REQUESTS_QUERY);
            while (rs.next()) {
                String lrId = rs.getString("LR_ID");
                String lrEmployeeID = rs.getString("LR_EmployeeID");
                String lrDuration = rs.getString("LR_Duration");
                String lrType = rs.getString("LR_Type");
                String isAccepted = rs.getString("isAccepted");
                String leaveRequestDate = rs.getString("Leave_Request_Date");
                String leaveEndDate = rs.getString("Leave_End_Date");
                LeaveRequest tempLeaveRequest = new LeaveRequest(lrId, lrEmployeeID, lrDuration, lrType, leaveEndDate, leaveRequestDate, leaveEndDate);
                tempLeaveRequest.setIsAccepted(isAccepted);
                modelLeaveRequest.put(lrId, tempLeaveRequest);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,LeaveRequest> getAllLeaves(){
        return new HashMap<>(modelLeaveRequest);
    }

    public LeaveRequest getOneLeave(String id){
        return modelLeaveRequest.get(id);
    }

    public boolean insert(LeaveRequest leaveRequest ){
        String query=DBQueriesConstant.INSERT_LEAVE_REQUEST_QUERY +" values('"+leaveRequest.getLrEmployeeid()+"','"
                +leaveRequest.getLrDuration()+"','"+leaveRequest.getLrType()+"','"+leaveRequest.getLeaveRequestDate()+"','"+leaveRequest.getLeaveEndDate()+"')";
        Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        if(insertStatus){
            Integer maxLR_ID=0;
            for(Map.Entry<String, LeaveRequest> tmpLR:modelLeaveRequest.entrySet()){
                if(maxLR_ID<Integer.parseInt(tmpLR.getKey())){
                    maxLR_ID=Integer.parseInt(tmpLR.getKey());
                }
            }
            maxLR_ID=maxLR_ID+1;
            System.out.println(maxLR_ID);
            leaveRequest.setLrId(maxLR_ID.toString());

            modelLeaveRequest.put(maxLR_ID.toString(),leaveRequest);
        }
        return insertStatus;
    }

    public boolean updateLeaveTrue(LeaveRequest leaveRequest) {
       Boolean updateStatus =DatabaseConnection.getInstance().updateData(DBQueriesConstant.UPDATE_LEAVE_REQUEST_TRUE_QUERY +"\""+leaveRequest.getLrId()+"\";");
       if(updateStatus){
           leaveRequest.setIsAccepted("1");
           modelLeaveRequest.put(leaveRequest.getLrId(),leaveRequest);
       }
        return updateStatus;
    }

    public boolean updateLeaveFalse(LeaveRequest leaveRequest){
        Boolean updateStatus =DatabaseConnection.getInstance().updateData(DBQueriesConstant.UPDATE_LEAVE_REQUEST_FALSE_QUERY +"\""+leaveRequest.getLrId()+"\";");
        if(updateStatus){
            leaveRequest.setIsAccepted("0");
            modelLeaveRequest.put(leaveRequest.getLrId(),leaveRequest);
        }
        return updateStatus;
    }

}
