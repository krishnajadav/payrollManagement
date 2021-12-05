package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerReimbursementDAO extends EmployeeReimbursementDAO {

	public List<ReimbursementRequest> getSpecificEmployeeReimbursements(String EmployeeID) throws SQLException {
		List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();

		String query = "select * from `Reimbursement_Request` where RR_EmployeeID=\"" + EmployeeID;
		try {
			mySQLDB.LoadDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet allReimbursements = mySQLDB.ExecuteQuery(query);
		while (allReimbursements.next()) {
			String RR_EmployeeID = allReimbursements.getString("RR_EmployeeID");
			int RR_TypeID = allReimbursements.getInt("RR_TypeID");
			String RR_Note = allReimbursements.getString("RR_Note");
			int RR_Amount = allReimbursements.getInt("RR_Amount");
			Date RR_Date = allReimbursements.getDate("RR_Date");
			int isAccepted = allReimbursements.getInt("isAccepted");
			ReimbursementRequest obj = new ReimbursementRequest(RR_EmployeeID, RR_TypeID, RR_Note, RR_Amount, RR_Date,
					isAccepted);
			reimbursementRequests.add(obj);
		}
		return reimbursementRequests;
	}

	public boolean updateReimbursementRequest(String ManagerID, ReimbursementRequest rr, int UpdateAccepted) throws SQLException {
		String query = "select 'ManagerID' from `Employee` where RR_EmployeeID=\"" + rr.getEmployeeID();
		try {
			mySQLDB.LoadDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet manager = mySQLDB.ExecuteQuery(query);
		String employeeManagerID = null;
		while (manager.next()) {
			employeeManagerID = manager.getString("ManagerID");
		}
		if (ManagerID != employeeManagerID) {
			return false;
		}
		rr.setIsAccepted(UpdateAccepted);
		return true;

	}
}
