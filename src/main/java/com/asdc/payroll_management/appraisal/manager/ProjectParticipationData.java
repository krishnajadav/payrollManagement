package com.asdc.payroll_management.appraisal.manager;

public class ProjectParticipationData {

	String projectName;
	String projectSize;
	String employeeContribution;
	String error;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectSize() {
		return projectSize;
	}

	public void setProjectSize(String projectSize) {
		this.projectSize = projectSize;
	}

	public String getEmployeeContribution() {
		return employeeContribution;
	}

	public void setEmployeeContribution(String employeeContribution) {
		this.employeeContribution = employeeContribution;
	}

	public String getError() {
		return this.error;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();

		if (this.projectName == null || this.projectName.isEmpty()) {
			sb.append("Project Name cannot be empty.<br>");
		}
		if (this.employeeContribution == null || this.employeeContribution.isEmpty()) {
			sb.append("Employee contriution in project cannot be empty.<br>");
		}
		if (!isValidNumber(employeeContribution)) {
			sb.append("Employee contribution should be a valid number that is greater than 0 and less than 100.<br>");
		}
		if (!isValidSize(projectSize)) {
			sb.append("Project size should be Small scale, Medium scale or Large scale.<br>");
		}

		this.error = sb.toString().isEmpty() ? null : sb.toString();
	}

	private boolean isValidNumber(String numberInStringFormat) {
		try {
			if (Double.parseDouble(numberInStringFormat) <= 0d || Double.parseDouble(numberInStringFormat) > 100d) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidSize(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		} else {
			if (!(name.equals("Small scale") || name.equals("Medium scale") || name.equals("Large scale"))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "projectName : " + projectName + " || projectSize : " + projectSize + " || employeeContribution : "
				+ employeeContribution + " || error : " + error;
	}
}
