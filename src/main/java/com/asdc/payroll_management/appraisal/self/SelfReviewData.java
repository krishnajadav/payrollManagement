package com.asdc.payroll_management.appraisal.self;

import java.util.List;

public class SelfReviewData {

	List<String> technologiesLearnt;
	List<String> projectsParticipated;
	String comments;
	String rating;
	String employeeName;
	String employeeID = null;
	String error = null;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public List<String> getTechnologiesLearnt() {
		return technologiesLearnt;
	}

	public void setTechnologiesLearnt(List<String> technologiesLearnt) {
		this.technologiesLearnt = technologiesLearnt;
	}

	public List<String> getProjectsParticipated() {
		return projectsParticipated;
	}

	public void setProjectsParticipated(List<String> projectsParticipated) {
		this.projectsParticipated = projectsParticipated;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getError() {
		return error;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();

		if (!isValidName(this.employeeName)) {
			sb.append("Employee Name should only have alphabets and it cannot be empty.<br>");
		}
		if (this.comments.length() < 100) {
			sb.append("Self Review Comments should be atleast 100 characters.<br>");
		}
		if (!isValidNumber(rating)) {
			sb.append("Self Rating should be a valid number that is greater than 0 and less than 5.<br>");
		}
		if (this.projectsParticipated == null || this.projectsParticipated.size() <= 0) {
			sb.append("Atleast one project you participated in should be added.<br>");
		}

		this.error = sb.toString().isEmpty() ? null : sb.toString();
	}

	private boolean isValidNumber(String numberInStringFormat) {
		try {
			if (Double.parseDouble(numberInStringFormat) <= 0d || Double.parseDouble(numberInStringFormat) > 5d) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < name.length(); i++) {
				if (!Character.isLetter(name.charAt(i))) {
					if (name.charAt(i) == ' ') {
						continue;
					}
					return false;
				}
			}
		}
		return true;
	}

}
