package com.asdc.payroll_management.DataBaseCache;

public class DBQueriesConstant {
    public static String allEmployeesQuery = "Select * from Employee;";
    public static String insertEmployeeQuery = "Insert into Employee";
    public static String updateEmployeeQuery = "update Employee";

    public static String allHRQuery = "Select * from HR;";
    public static String insertHRQuery = "Insert into HR ";

    public static String allLeaveRequestsQuery = "Select * from  Leave_Request;";
    public static String insertLeaveRequestQuery = "Insert into Leave_Request (LR_EmployeeID ,LR_Duration,LR_Type,Leave_Request_Date,Leave_End_Date)";
    public static String updateLeaveRequestTrueQuery = "update Leave_Request set isAccepted=true where LR_ID=";
    public static String updateLeaveRequestFalseQuery = "update Leave_Request set isAccepted=false where LR_ID=";

    public static String allDepartmentQuery = "Select * from Department;";

    public static String allLeavesQuery = "Select * from Leaves;";

    public static String allManagersQuery = "Select * from Managers;";

    public static String allReimbursementRequestsQuery = "Select * from  Reimbursement_Request;";
    public static String insertReimbursementRequestsQuery = "Insert into Reimbursement_Request (RR_EmployeeID,RR_TypeID,RR_Note,RR_Amount,RR_Date,isAccepted)";
    public static String updateReimbursementRequestsTrueQuery = "update Reimbursement_Request set isAccepted=true where RR_ID=";
    public static String updateReimbursementRequestsFalseQuery = "update Reimbursement_Request set isAccepted=false where RR_ID=";

    public static String allReimbursementQuery = "Select * from Reimbursements_Type;";

    public static String allSalaryHistQuery = "SELECT * FROM Salary_Hist;";

    public static String allAppraisalQuery = "SELECT * FROM Appraisal;";
    public static String insertAppraisalQuery = "Insert into Appraisal (employee_ID,manager_ID,employee_rating,eployee_comments,manager_rating,manager_comments,employee_projects,technologies_learnt,final_rating,communication_rating,projects_rating)";
    public static String updateAppraisalQuery = "update Appraisal set";
    
    public static String allJobDesignations = "SELECT Designation_Name FROM Job_Designation;";

}
