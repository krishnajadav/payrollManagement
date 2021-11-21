package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;


public class DatabaseDML {

    public Database db;
    public Connection conn;

    public DatabaseDML(Database db){

        this.db=db;
        conn= this.db.dbconnection();
    }

    public ResultSet ViewqueryResultset(String query) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }


    public Boolean InsertLRResultset(LeaveRequest lr) throws SQLException {

        try {

            String query = " INSERT INTO `CSCI5308_17_DEVINT`.`Leave_Request` " +
                    "(`LR_EmployeeID`,`LR_Duration`,`LR_Type`,`Leave_Request_Date`,`Leave_End_Date`)"
                    + " values (?, ?, ?, ?, ?)";

            java.sql.Date sqlStartDate =new java.sql.Date(lr.getLeaveStartdate().getTime());
            java.sql.Date sqlEndDate =new java.sql.Date(lr.getLeaveEndDate().getTime());


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, lr.getEmployeeID());
            preparedStmt.setInt(2, lr.getLeaveDuration());
            preparedStmt.setInt(3, lr.getLeaveTypeID());
            preparedStmt.setDate(4, sqlStartDate);
            preparedStmt.setDate(5,sqlEndDate);
            Statement stmt = conn.createStatement();
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void closeDBconnection() throws SQLException {
        this.db.closeConnection(conn);

    }


}

