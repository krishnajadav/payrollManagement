package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;

public class AppraisalCache {
	private static HashMap<String, Appraisal> modelAppraisals = new HashMap<>();
	private static AppraisalCache appraisalFactory = null;

	public static AppraisalCache getInstance() {
		if (appraisalFactory == null) {
			appraisalFactory = new AppraisalCache();
		}
		return appraisalFactory;
	}

	private AppraisalCache() {
		load();
	}

	private void load() {

		try {
			ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allAppraisalQuery);
			while (rs.next()) {
				String employee_ID = rs.getString("employee_ID");
				String manager_ID = rs.getString("manager_ID");
				String employee_rating = rs.getString("employee_rating");
				String eployee_comments = rs.getString("eployee_comments");
				String manager_rating = rs.getString("manager_rating");
				String manager_comments = rs.getString("manager_comments");
				String employee_projects = rs.getString("employee_projects");
				String technologies_learnt = rs.getString("technologies_learnt");
				String final_rating = rs.getString("final_rating");
				String communication_rating = rs.getString("communication_rating");
				String projects_rating = rs.getString("projects_rating");

				Appraisal tempAppraisal = new Appraisal(employee_ID, manager_ID, employee_rating, eployee_comments,
						manager_rating, manager_comments, employee_projects, technologies_learnt, final_rating,
						communication_rating, projects_rating);
				// tempLeaveRequest.setIsAccepted(isAccepted);
				modelAppraisals.put(employee_ID, tempAppraisal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Appraisal> getAppraisals() {

		return new HashMap<>(modelAppraisals);
	}

	public Appraisal getIndividualAppraisals(String id) {

		return modelAppraisals.get(id);
	}

	public boolean insert(Appraisal e) {
		String query = DBQueriesConstant.insertAppraisalQuery + " values('" + e.getEmployee_ID() + "','"
				+ e.getManager_ID() + "','" + e.getEmployee_rating() + "','" + e.getEployee_comments() + "','"
				+ e.getManager_rating() + "','" + e.getManager_comments() + "','" + e.getEmployee_projects() + "','"
				+ e.getTechnologies_learnt() + "','" + e.getFinal_rating() + "','" + e.getCommunication_rating() + "','"
				+ e.getProjects_rating() + "')";
		Boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {

			modelAppraisals.put(e.getEmployee_ID(), e);
		}
		return insertStatus;
	}

	public boolean updateManagerReview(Appraisal appraisal) {
		String query = DBQueriesConstant.updateAppraisalQuery + " manager_ID='" + appraisal.getManager_ID()
				+ "', manager_rating='" + appraisal.getManager_rating() + "', manager_comments='"
				+ appraisal.getManager_comments() + "', communication_rating='" + appraisal.getCommunication_rating()
				+ "',projects_rating='" + appraisal.getProjects_rating() + "' where employee_ID = '"
				+ appraisal.getEmployee_ID() + "';";
		Boolean updateStatus = DatabaseConnection.getInstance().updateData(query);
		if (updateStatus) {
			Appraisal appraisalInCache = modelAppraisals.get(appraisal.getEmployee_ID());
			appraisalInCache.setManager_ID(appraisal.getManager_ID());
			appraisalInCache.setManager_comments(appraisal.getManager_comments());
			appraisalInCache.setManager_rating(appraisal.getManager_rating());
			appraisalInCache.setCommunication_rating(appraisal.getCommunication_rating());
			appraisalInCache.setProjects_rating(appraisal.getProjects_rating());
			modelAppraisals.replace(appraisal.getEmployee_ID(), appraisalInCache);
		}
		return updateStatus;
	}
	
	public boolean updateFinalRating(Double finalRating, String employeeCode) {
		String query = DBQueriesConstant.updateAppraisalQuery + " final_rating='" + finalRating
				+ "' where employee_ID = '"
				+ employeeCode + "';";
		Boolean updateStatus = DatabaseConnection.getInstance().updateData(query);
		if (updateStatus) {
			Appraisal appraisalInCache = modelAppraisals.get(employeeCode);
			appraisalInCache.setFinal_rating(String.valueOf(finalRating));
			modelAppraisals.replace(employeeCode, appraisalInCache);
		}
		return updateStatus;
	}

}
