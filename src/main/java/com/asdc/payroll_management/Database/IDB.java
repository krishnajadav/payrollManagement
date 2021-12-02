package com.asdc.payroll_management.Database;

import java.sql.*;

public interface IDB {

	void LoadDatabase();

	ResultSet ExecuteQuery(String query);

	public boolean InsertResultset(String query, PreparedStatement preparedStmt);

	public Connection getConnection();

	void Close();

}
