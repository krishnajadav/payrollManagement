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
			ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.ALL_APPRAISAL_QUERY);
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

	public boolean insert(Appraisal appraisal) {
		String query = DBQueriesConstant.INSERT_APPRAISAL_QUERY + " values('" + appraisal.getEmployeeID() + "','"
				+ appraisal.getManagerID() + "','" + appraisal.getEmployeeRating() + "','"
				+ appraisal.getEployeeComments() + "','" + appraisal.getManagerRating() + "','"
				+ appraisal.getManagerComments() + "','" + appraisal.getEmployeeProjects() + "','"
				+ appraisal.getTechnologiesLearnt() + "','" + appraisal.getFinalRating() + "','"
				+ appraisal.getCommunicationRating() + "','" + appraisal.getProjectsRating() + "')";
		Boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {

			modelAppraisals.put(appraisal.getEmployeeID(), appraisal);
		}
		return insertStatus;
	}

	public boolean updateManagerReview(Appraisal appraisal) {
		String query = DBQueriesConstant.UPDATE_APPRAISAL_QUERY + " manager_ID='" + appraisal.getManagerID()
				+ "', manager_rating='" + appraisal.getManagerRating() + "', manager_comments='"
				+ appraisal.getManagerComments() + "', communication_rating='" + appraisal.getCommunicationRating()
				+ "',projects_rating='" + appraisal.getProjectsRating() + "' where employee_ID = '"
				+ appraisal.getEmployeeID() + "';";
		Boolean updateStatus = DatabaseConnection.getInstance().updateData(query);
		if (updateStatus) {
			Appraisal appraisalInCache = modelAppraisals.get(appraisal.getEmployeeID());
			appraisalInCache.setManagerID(appraisal.getManagerID());
			appraisalInCache.setManagerComments(appraisal.getManagerComments());
			appraisalInCache.setManagerRating(appraisal.getManagerRating());
			appraisalInCache.setCommunicationRating(appraisal.getCommunicationRating());
			appraisalInCache.setProjectsRating(appraisal.getProjectsRating());
			modelAppraisals.replace(appraisal.getEmployeeID(), appraisalInCache);
		}
		return updateStatus;
	}

	public boolean updateFinalRating(Double finalRating, String employeeCode) {
		String query = DBQueriesConstant.UPDATE_APPRAISAL_QUERY + " final_rating='" + finalRating
				+ "' where employee_ID = '" + employeeCode + "';";
		Boolean updateStatus = DatabaseConnection.getInstance().updateData(query);
		if (updateStatus) {
			Appraisal appraisalInCache = modelAppraisals.get(employeeCode);
			appraisalInCache.setFinalRating(String.valueOf(finalRating));
			modelAppraisals.replace(employeeCode, appraisalInCache);
		}
		return updateStatus;
	}

}
