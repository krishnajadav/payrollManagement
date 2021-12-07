package com.asdc.payroll_management.appraisal.manager;

import java.util.List;

public class ManagerReviewData {

	String communicationSkillsRating;
	List<ProjectParticipationData> projectsParticipated;
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

	public String getCommunicationSkillsRating() {
		return communicationSkillsRating;
	}

	public void setCommunicationSkillsRating(String communicationSkillsRating) {
		this.communicationSkillsRating = communicationSkillsRating;
	}

	public void setProjectsParticipated(List<ProjectParticipationData> projectsParticipated) {
		this.projectsParticipated = projectsParticipated;
	}

	public List<ProjectParticipationData> getProjectsParticipated() {
		return projectsParticipated;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();

		if (!isValidName(this.employeeName)) {
			sb.append("Employee Name should only have alphabets and it cannot be empty.<br>");
		}
		if (!isValidNumber(this.rating)) {
			sb.append("Manager Rating should be a valid number that is greater than 0 and less than 5.<br>");
		}

		if (!isValidNumber(this.communicationSkillsRating)) {
			sb.append(
					"Communication skills Rating should be a valid number that is greater than 0 and less than 5.<br>");
		}

		if (this.projectsParticipated == null || this.projectsParticipated.size() <= 0) {
			sb.append("Atleast one project you participated in should be added.<br>");
		} else {
			for (ProjectParticipationData projectData : this.projectsParticipated) {
				projectData.validate();
				if (projectData.getError() != null) {
					sb.append(projectData.getError());
					break;
				}
			}
		}

		if (this.comments.length() < 100) {
			sb.append("Manager Review Comments should be atleast 100 characters.<br>");
		}

		error = sb.toString().isEmpty() ? null : sb.toString();
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
