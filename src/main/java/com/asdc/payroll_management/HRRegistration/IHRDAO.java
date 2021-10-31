package com.asdc.payroll_management.HRRegistration;

import java.sql.*;
import java.util.List;

public interface IHRDAO {
	
	 List<HR> getAllHRs() throws SQLException;
	 
	 String saveHR(HR hr);

}
