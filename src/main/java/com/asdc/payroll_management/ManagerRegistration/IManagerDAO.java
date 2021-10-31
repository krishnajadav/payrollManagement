package com.asdc.payroll_management.ManagerRegistration;

import java.sql.*;
import java.util.List;

public interface IManagerDAO {
	
	 List<Manager> getAllManagers() throws SQLException;
	 
	 String saveManager(Manager emp);

}
