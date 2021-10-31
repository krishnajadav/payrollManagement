package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeConcreteFactory extends EmployeeAbstractFactory {

@Override
    public List<Employee> getAllEmployees() {
	
        EmployeeDAO EmpDAO=new EmployeeDAO();
        List<Employee> Employees = new ArrayList<Employee>();
        
		try {
			Employees = EmpDAO.getAllEmployees();
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
