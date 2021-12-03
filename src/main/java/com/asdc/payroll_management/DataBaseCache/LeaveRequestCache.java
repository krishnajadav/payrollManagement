package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allLeaveRequestsQuery);
            while (rs.next()) {
                String LR_ID = rs.getString("LR_ID");
                String LR_EmployeeID = rs.getString("LR_EmployeeID");
                String LR_Duration = rs.getString("LR_Duration");
                String LR_Type = rs.getString("LR_Type");
                String isAccepted = rs.getString("isAccepted");
                String Leave_Request_Date = rs.getString("Leave_Request_Date");
                String Leave_End_Date = rs.getString("Leave_End_Date");
                LeaveRequest tempLeaveRequest = new LeaveRequest(LR_ID, LR_EmployeeID, LR_Duration, LR_Type, Leave_End_Date, Leave_Request_Date, Leave_End_Date);
                tempLeaveRequest.setIsAccepted(isAccepted);
                modelLeaveRequest.put(LR_ID, tempLeaveRequest);

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

    public boolean insert(LeaveRequest e ){
        String query=DBQueriesConstant.insertLeaveRequestQuery+" values('"+e.getLR_EmployeeID()+"','"
                +e.getLR_Duration()+"','"+e.getLR_Type()+"','"+e.getLeave_Request_Date()+"','"+e.getLeave_End_Date()+"')";
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
            e.setLR_ID(maxLR_ID.toString());

            modelLeaveRequest.put(maxLR_ID.toString(),e);
        }
        return insertStatus;
    }

    public boolean updateLeaveTrue(LeaveRequest e) {
       Boolean updateStatus =DatabaseConnection.getInstance().updateData(DBQueriesConstant.updateLeaveRequestTrueQuery+"\""+e.getLR_ID()+"\";");
       if(updateStatus){
           e.setIsAccepted("1");
           modelLeaveRequest.put(e.getLR_ID(),e);
       }
        return updateStatus;
    }

    public boolean updateLeaveFalse(LeaveRequest e){
        Boolean updateStatus =DatabaseConnection.getInstance().updateData(DBQueriesConstant.updateLeaveRequestFalseQuery+"\""+e.getLR_ID()+"\";");
        if(updateStatus){
            e.setIsAccepted("0");
            modelLeaveRequest.put(e.getLR_ID(),e);
        }
        return updateStatus;
    }

}
