package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.Database.MySQLDB;

public class EmployeeDAO implements IEmployeeDAO {

	  MySQLDB mySQLDB = new MySQLDB();
	
	@Override
    public List<Employee> getAllEmployees() throws SQLException {
		
        List<Employee> Employees= new ArrayList<Employee>();
        mySQLDB.LoadDatabase();
        ResultSet rs= mySQLDB.ExecuteQuery("SELECT * FROM Employee;");
        
        while (rs.next()){
        	
        	Employee Emp = new Employee();
        	Emp.setEmployee_ID(rs.getString("Employee_ID"));
        	Emp.setEmployee_Name(rs.getString("Employee_Name"));
        	Emp.setEmployee_emailID(rs.getString("Employee_emailID"));
        	Emp.setEmployee_Password(rs.getString("Employee_Password"));
        	Emp.setEmployee_Address(rs.getString("Employee_Address"));
        	Emp.setEmployee_phoneNumb(rs.getString("Employee_phoneNumb"));
        	Emp.setJd_ID(rs.getInt("JD_ID"));
        	Emp.setManagerID(rs.getString("ManagerID"));
        	Emp.setDepartment_ID(rs.getInt("Department_ID"));
        	
            Employees.add(Emp);

        }
        
        return Employees;
        
    }
	
	
	@Override
    public String saveEmployee(Employee emp)
    {	
		try {
			mySQLDB.LoadDatabase();
			String callST="{call SP_saveEmployee('"+emp.getEmployee_ID()+"','"+
					emp.getEmployee_Name()+"','"+emp.getEmployee_emailID()+"','"+
					emp.getEmployee_Password()+"','"+emp.getEmployee_Address()+"','"+
					emp.getEmployee_phoneNumb()+"','"+emp.getManagerID()+"',"+emp.getDepartment_ID()+","+
					emp.getJd_ID()+")}";
			mySQLDB.ExecuteQuery(callST);
			
			return "Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
    }
	
		
}
