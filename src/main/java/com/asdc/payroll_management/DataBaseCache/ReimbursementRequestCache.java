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
				String RR_ID = rs.getString("RR_ID");
				String RR_EmployeeID = rs.getString("RR_EmployeeID");
				String RR_TypeID = rs.getString("RR_TypeID");
				String RR_Note = rs.getString("RR_Note");
				String RR_Amount = rs.getString("RR_Amount");
				String RR_Date = rs.getString("RR_Date");
				String isAccepted = rs.getString("isAccepted");
				ReimbursementRequest tempRR = new ReimbursementRequest(RR_ID, RR_EmployeeID, RR_TypeID, RR_Note,
						RR_Amount, RR_Date, isAccepted);
				modelReimbursementRequests.put(RR_ID, tempRR);
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
				+ reimbursementRequest.getRR_EmployeeID() + "','" + reimbursementRequest.getRR_TypeID() + "','"
				+ reimbursementRequest.getRR_Note() + "','" + reimbursementRequest.getRR_Amount() + "','"
				+ reimbursementRequest.getRR_Date() + "','" + reimbursementRequest.getIsAccepted() + "')";
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

	public boolean updateReimbursementRequestTrue(ReimbursementRequest e) {
		Boolean updateStatus = DatabaseConnection.getInstance()
				.updateData(DBQueriesConstant.UPDATE_REIMBURSEMENT_REQUESTS_TRUE_QUERY + "\"" + e.getRR_ID() + "\";");
		if (updateStatus) {
			e.setIsAccepted("1");
			modelReimbursementRequests.put(e.getRR_ID(), e);
		}
		return updateStatus;
	}

	public boolean updateReimbursementRequestFalse(ReimbursementRequest e) {
		Boolean updateStatus = DatabaseConnection.getInstance()
				.updateData(DBQueriesConstant.UPDATE_REIMBURSEMENT_REQUESTS_FALSE_QUERY + "\"" + e.getRR_ID() + "\";");
		if (updateStatus) {
			e.setIsAccepted("0");
			modelReimbursementRequests.put(e.getRR_ID(), e);
		}
		return updateStatus;
	}

}
