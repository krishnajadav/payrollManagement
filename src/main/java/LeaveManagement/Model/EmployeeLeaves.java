package LeaveManagement.Model;


public class EmployeeLeaves {

    private String EmployeeID;
    private String EmployeeName;
    private String ManagerID;

    public EmployeeLeaves(String EMPID, String EMPNAME, String ManagerID){
        this.EmployeeID=EMPID;
        this.EmployeeName=EMPNAME;
        this.ManagerID=ManagerID;

    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }


}

