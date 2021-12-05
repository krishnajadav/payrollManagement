package com.asdc.payroll_management.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDB implements IDB {

	private Connection con;
	private ResultSet rs;
	private Statement st;

	@Override
	public void LoadDatabase() throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");		
			con = DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_DEVINT",
					"CSCI5308_17_DEVINT_USER", "aiJ9Eidoo1kieyej");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ResultSet ExecuteQuery(String query) {

		if (con == null) {
			try {
				LoadDatabase();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	@Override
	public void Close() {

		try {

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean InsertResultset(String query, PreparedStatement preparedStmt) {
		boolean success = false;
		if (con == null) {
			try {
				LoadDatabase();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			preparedStmt.execute();
			if (rs != null)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Connection getConnection() {
		return con;
	}

}
