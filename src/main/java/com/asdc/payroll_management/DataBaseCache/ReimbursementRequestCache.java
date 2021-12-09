package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ReimbursementRequestCache {
	private static HashMap<String, ReimbursementRequest> modelReimbursementRequests = new HashMap<>();
	private static ReimbursementRequestCache reimbursementRequestCache = null;

	public static ReimbursementRequestCache getInstance() {
		if (reimbursementRequestCache == null) {
			reimbursementRequestCache = new ReimbursementRequestCache();
		}
		return reimbursementRequestCache;
	}

	private ReimbursementRequestCache() {
		load();
	}

	private void load() {

		try {
			ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_REIMBURSEMENT_REQUESTS_QUERY);
			while (rs.next()) {
				String rrId = rs.getString("RR_ID");
				String rrEmployeeID = rs.getString("RR_EmployeeID");
				String rrTypeID = rs.getString("RR_TypeID");
				String rrNote = rs.getString("RR_Note");
				String rrAmount = rs.getString("RR_Amount");
				String rrDate = rs.getString("RR_Date");
				String isAccepted = rs.getString("isAccepted");
				ReimbursementRequest tempRR = new ReimbursementRequest(rrId, rrEmployeeID, rrTypeID, rrNote, rrAmount,
						rrDate, isAccepted);
				// tempLeaveRequest.setIsAccepted(isAccepted);
				modelReimbursementRequests.put(rrId, tempRR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, ReimbursementRequest> getAllReimbursementRequest() {

		return new HashMap<>(modelReimbursementRequests);
	}

	public ReimbursementRequest getOne(String id) {

		return modelReimbursementRequests.get(id);
	}

	public boolean insert(ReimbursementRequest reimbursementRequest) {
		String query = DBQueriesConstant.INSERT_REIMBURSEMENT_REQUESTS_QUERY + " values('"
				+ reimbursementRequest.getRrEmployeeid() + "','"
				+ reimbursementRequest.getRRTypeID() + "','" + reimbursementRequest.getRrNote() + "','"
				+ reimbursementRequest.getRrAmount() + "','" + reimbursementRequest.getRrDate() + "','"
				+ reimbursementRequest.getIsAccepted() + "')";
		Boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {
			Integer maxRR_ID = 0;
			for (Map.Entry<String, ReimbursementRequest> tmpLR : modelReimbursementRequests.entrySet()) {
				if (maxRR_ID < Integer.parseInt(tmpLR.getKey())) {
					maxRR_ID = Integer.parseInt(tmpLR.getKey());
				}
			}
			maxRR_ID = maxRR_ID + 1;

			modelReimbursementRequests.put(maxRR_ID.toString(), reimbursementRequest);
		}
		return insertStatus;
	}

	public boolean updateReimbursementRequestTrue(ReimbursementRequest reimbursementRequest) {
		Boolean updateStatus = DatabaseConnection.getInstance()
				.updateData(DBQueriesConstant.UPDATE_REIMBURSEMENT_REQUESTS_TRUE_QUERY + "\""
						+ reimbursementRequest.getRrId() + "\";");
		if (updateStatus) {
			reimbursementRequest.setIsAccepted("1");
			modelReimbursementRequests.put(reimbursementRequest.getRrId(), reimbursementRequest);
		}
		return updateStatus;
	}

	public boolean updateReimbursementRequestFalse(ReimbursementRequest reimbursementRequest) {
		Boolean updateStatus = DatabaseConnection.getInstance()
				.updateData(DBQueriesConstant.UPDATE_REIMBURSEMENT_REQUESTS_FALSE_QUERY + "\""
						+ reimbursementRequest.getRrId() + "\";");
		if (updateStatus) {
			reimbursementRequest.setIsAccepted("0");
			modelReimbursementRequests.put(reimbursementRequest.getRrId(), reimbursementRequest);
		}
		return updateStatus;
	}

}
