package com.asdc.payroll_management.DataBaseCache;

import java.sql.*;

public class DatabaseConnection {
	
	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_PRODUCTION";
	private static final String USER_NAME = "CSCI5308_17_PRODUCTION_USER";
	private static final String PASSWORD = "Peguo6ier4daev8y";

    private Connection conn;
    public static DatabaseConnection instance = null;




    public static DatabaseConnection getInstance(){
        if (instance == null){
            instance= new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Database Connection Failed");
        }
        }

    public ResultSet getData(String query){

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false) {
                return null;
            } else {
                rs.beforeFirst();
            }

            return rs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateData(String query) {

        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertData(String query) {
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public  void closeConnection(){
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
