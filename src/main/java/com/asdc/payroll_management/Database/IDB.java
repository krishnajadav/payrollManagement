package com.asdc.payroll_management.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface IDB {

	void LoadDatabase();

    ResultSet ExecuteQuery(String query);

    void Close();

	
}
