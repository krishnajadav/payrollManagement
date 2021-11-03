package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;


public class DatabaseDML {

    public Connection conn;

    public DatabaseDML(String host, String port, String pwd, String username, String DatabaseName){
        Database db = new Database(host, port, pwd, username, DatabaseName);
        conn= db.dbconnection();
    }

    public ResultSet ViewqueryResultset(String query) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public Boolean InsertLRResultset(LeaveRequest lr) throws SQLException {

        try {

            String query = " INSERT INTO `CSCI5308_17_DEVINT`.`Leave_Request` " +
                    "(`LR_EmployeeID`,`LR_Duration`,`LR_Type`,`isAccepted`,`Leave_Request_Date`,`Leave_End_Date`)"
                    + " values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, lr.getEmployeeID());
            preparedStmt.setInt(2, lr.getLeaveDuration());
            preparedStmt.setInt(3, lr.getLeaveTypeID());
            preparedStmt.setBoolean(4, lr.isAccepted());
            preparedStmt.setDate(5, (java.sql.Date) lr.getLeaveStartdate());
            preparedStmt.setDate(6, (java.sql.Date) lr.getLeaveEndDate());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


}

