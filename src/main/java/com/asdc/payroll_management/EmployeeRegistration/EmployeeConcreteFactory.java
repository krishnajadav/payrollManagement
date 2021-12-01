package com.asdc.payroll_management.EmployeeRegistration;


public class EmployeeConcreteFactory extends EmployeeAbstractFactory {

@Override
public String saveEmployee(Employee emp) {

    EmployeeDAO EmpDAO=new EmployeeDAO();
    
    return EmpDAO.saveEmployee(emp);

}

    
}
