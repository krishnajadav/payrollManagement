package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.SQLException;
import java.util.List;

public class EmployeeConcreteFactory extends EmployeeAbstractFactory {

@Override
    public List<Employee> getAllEmployees() {
	
        EmployeeDAO EmpDAO=new EmployeeDAO();
        List<Employee> Employees = null;
        
		try {
			
			Employees = EmpDAO.getAllEmployees();
	        for(Employee EMP:Employees) {
	            System.out.println(EMP.getEmployee_Name());
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return Employees;
    }	

@Override
public String saveEmployee(Employee emp) {

    EmployeeDAO EmpDAO=new EmployeeDAO();
    	
    return EmpDAO.saveEmployee(emp);

}

    
}
