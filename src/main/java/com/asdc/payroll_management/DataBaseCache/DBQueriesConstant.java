package com.asdc.payroll_management.DataBaseCache;

public class DBQueriesConstant {
	public final static String All_EMPLOYEE_QUERY = "Select * from Employee;";
	public final static String INSERT_GENARATE_EMPLOYEE_QUERY = "Insert into Employee (Employee_ID,Employee_Name,Employee_Salary,ManagerID,Department_ID,Designation,Access_level) values ";
	public final static String INSERT_EMPLOYEE_QUERY = "Insert into Employee ";
	public final static String UPDATE_EMPLOYEE_QUERY = "update Employee";

	public final static String ALL_HR_QUERY = "Select * from HR;";
	public final static String INSERT_HR_QUERY = "Insert into HR ";

	public final static String ALL_LEAVE_REQUESTS_QUERY = "Select * from  Leave_Request;";
	public final static String INSERT_LEAVE_REQUEST_QUERY = "Insert into Leave_Request (LR_EmployeeID ,LR_Duration,LR_Type,Leave_Request_Date,Leave_End_Date)";
	public final static String UPDATE_LEAVE_REQUEST_TRUE_QUERY = "update Leave_Request set isAccepted=true where LR_ID=";
	public final static String UPDATE_LEAVE_REQUEST_FALSE_QUERY = "update Leave_Request set isAccepted=false where LR_ID=";

	public final static String ALL_DEPARTMENT_QUERY = "Select * from Department;";

	public final static String ALL_LEAVES_QUERY = "Select * from Leaves;";

	public final static String ALL_MANAGERS_QUERY = "Select * from Managers;";

	public final static String ALL_REIMBURSEMENT_REQUESTS_QUERY = "Select * from  Reimbursement_Request;";
	public final static String INSERT_REIMBURSEMENT_REQUESTS_QUERY = "Insert into Reimbursement_Request (RR_EmployeeID,RR_TypeID,RR_Note,RR_Amount,RR_Date,isAccepted)";
	public final static String UPDATE_REIMBURSEMENT_REQUESTS_TRUE_QUERY = "update Reimbursement_Request set isAccepted=true where RR_ID=";
	public final static String UPDATE_REIMBURSEMENT_REQUESTS_FALSE_QUERY = "update Reimbursement_Request set isAccepted=false where RR_ID=";

	public final static String ALL_REIMBURSEMENT_QUERY = "Select * from Reimbursements_Type;";

	public final static String ALL_SALARY_HIST_QUERY = "SELECT * FROM Salary_Hist;";

	public final static String ALL_APPRAISAL_QUERY = "SELECT * FROM Appraisal;";
	public final static String INSERT_APPRAISAL_QUERY = "Insert into Appraisal (employee_ID,manager_ID,employee_rating,eployee_comments,manager_rating,manager_comments,employee_projects,technologies_learnt,final_rating,communication_rating,projects_rating)";
	public final static String UPDATE_APPRAISAL_QUERY = "update Appraisal set";

	public final static String ALL_JOB_DESIGNATIONS = "SELECT Designation_Name FROM Job_Designation;";

}
