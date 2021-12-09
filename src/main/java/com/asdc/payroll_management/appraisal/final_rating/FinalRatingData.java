package com.asdc.payroll_management.appraisal.final_rating;

public class FinalRatingData {

	String employeeComments;
	double employeeRating;
	String managerComments;
	double managerRating;
	String managerName;
	String managerID = null;
	String employeeName;
	String employeeID = null;
	String error = null;
	Double finalRating;

	public FinalRatingData(String comments, double rating, String managerComments, double managerRating,
			String managerName, String managerID, String employeeName, String employeeID, Double finalRating) {
		super();
		this.employeeComments = comments;
		this.employeeRating = rating;
		this.managerComments = managerComments;
		this.managerRating = managerRating;
		this.managerName = managerName;
		this.managerID = managerID;
		this.employeeName = employeeName;
		this.employeeID = employeeID;
		this.finalRating = finalRating;
	}

	public double getEmployeeRating() {
		return employeeRating;
	}

	public void setEmployeeRating(double employeeRating) {
		this.employeeRating = employeeRating;
	}

	public String getEmployeeComments() {
		return employeeComments;
	}

	public void setEmployeeComments(String comments) {
		this.employeeComments = comments;
	}

	public String getManagerComments() {
		return managerComments;
	}

	public void setManagerComments(String managerComments) {
		this.managerComments = managerComments;
	}

	public double getManagerRating() {
		return managerRating;
	}

	public void setManagerRating(double managerRating) {
		this.managerRating = managerRating;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Double getFinalRating() {
		return finalRating;
	}

	public void setFinalRating(Double finalRating) {
		this.finalRating = finalRating;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();

		if (!isValidName(this.employeeName)) {
			sb.append("Employee Name should only have alphabets and it cannot be empty.");
		}
		if (this.employeeComments.length() < 100) {
			sb.append("Self Review Comments should be atleast 100 characters.");
		}
		if (!isValidNumber(employeeRating)) {
			sb.append("Self Rating should be a valid number that is greater than 0 and less than 5.");
		}

		if (!isValidName(this.managerName)) {
			sb.append("Manager Name should only have alphabets and it cannot be empty.");
		}
		if (this.managerComments.length() < 100) {
			sb.append("Manager Review Comments should be atleast 100 characters.");
		}
		if (!isValidNumber(managerRating)) {
			sb.append("Manager Rating should be a valid number that is greater than 0 and less than 5.");
		}

		if (finalRating == null || !isValidNumber(finalRating)) {
			sb.append("Final Rating should be a valid number that is greater than 0 and less than 5.");
		}

		this.error = sb.toString().isEmpty() ? null : sb.toString();
	}

	private boolean isValidNumber(double numberInStringFormat) {
		try {
			if (numberInStringFormat <= 0d || numberInStringFormat > 5d) {
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
