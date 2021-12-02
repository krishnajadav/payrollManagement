package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asdc.payroll_management.Database.MySQLDB;

public class EmployeeReimbursementDAO implements IEmployeeReimbursementDAO {

	MySQLDB mySQLDB = new MySQLDB();

	@Override
	public List<ReimbursementRequest> getAllReimbursements(String EmployeeID) throws SQLException {
		List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
		mySQLDB.LoadDatabase();
		/*
		 * String employeeQuery =
		 * "select 'Employee_Name','ManagerID' from 'Employee' where 'EmployeeID'=" +
		 * EmployeeID; ResultSet employeeData = mySQLDB.ExecuteQuery(employeeQuery);
		 * ReimbursementEmployee emp = new ReimbursementEmployee(EmployeeID,
		 * employeeData.getString("Employee_Name"),
		 * employeeData.getString("ManagerID"));
		 */
		String query = "select * from `Leave_Request` where RR_EmployeeID='" + EmployeeID + "'";
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

	@Override
	public List<ReimbursementType> getAllReimbursementTypes() throws SQLException {
		List<ReimbursementType> rt = new ArrayList<>();
		String query = "select * from `Reimbursements_Type` ;";
		mySQLDB.LoadDatabase();
		ResultSet allReimbursementTypes = mySQLDB.ExecuteQuery(query);
		while (allReimbursementTypes.next()) {
			int id = allReimbursementTypes.getInt("RT_ID");
			String ReimbursementType = allReimbursementTypes.getString("Reimbursement_Type");
			int ReimbursementLimit = allReimbursementTypes.getInt("Reimbursement_Limit");

			ReimbursementType obj = new ReimbursementType(id, ReimbursementType, ReimbursementLimit);
			rt.add(obj);
		}
		return rt;
	}

	@Override
	public boolean addEmployeeReimbursement(ReimbursementRequest newReimbursementRqeust)
			throws ParseException, SQLException {
		mySQLDB.LoadDatabase();
		List<ReimbursementType> reimbursementTypes = this.getAllReimbursementTypes();
		ReimbursementType requestedType = null;
		for (ReimbursementType temp : reimbursementTypes) {
			if (temp.getId() == newReimbursementRqeust.getReimbursementTypeID()) {
				requestedType = temp;
				break;
			}
		}

		try {

			String query = " INSERT INTO `CSCI5308_17_DEVINT`.`Reimbursement_Request` "
					+ "(`RR_EmployeeID`,`RR_TypeID`,`RR_Note`,`RR_Amount`,`RR_Date`)" + " values (?, ?, ?, ?, ?)";

			Connection conn = mySQLDB.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, newReimbursementRqeust.getEmployeeID());
			preparedStmt.setInt(2, newReimbursementRqeust.getReimbursementTypeID());
			preparedStmt.setString(3, newReimbursementRqeust.getReimbursementNote());
			preparedStmt.setInt(4, newReimbursementRqeust.getReimbursementAmount());
			preparedStmt.setDate(5, (java.sql.Date) newReimbursementRqeust.getReimbursementDate());
			boolean insertStatus = mySQLDB.InsertResultset(query, preparedStmt);
			return insertStatus;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
