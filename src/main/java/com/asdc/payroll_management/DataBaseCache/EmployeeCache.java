package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;


public class EmployeeCache {

    private static HashMap<String,Employee> modelEmployees = new HashMap<String,Employee>();
    private static EmployeeCache employeeFactory=null;

    public static EmployeeCache getInstance(){
        if(employeeFactory==null){
            employeeFactory=new EmployeeCache();
        }
        return employeeFactory;
    }

    private EmployeeCache(){
        load();
    }


    private void load() {
        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allEmployeesQuery);
            while (rs.next()) {
                String Employee_ID = rs.getString("Employee_ID");
                String Employee_Name = rs.getString("Employee_Name");
                String Employee_emailID = rs.getString("Employee_emailID");
                String Employee_Password = rs.getString("Employee_Password");
                String Employee_Address = rs.getString("Employee_Address");
                String Employee_phoneNumb = rs.getString("Employee_phoneNumb");
                String Employee_Salary = rs.getString("Employee_Salary");
                String ManagerID = rs.getString("ManagerID");
                String Department_ID = rs.getString("Department_ID");
                String Designation = rs.getString("Designation");
                String Access_level = rs.getString("Access_level");
                modelEmployees.put(Employee_ID, new Employee(Employee_ID, Employee_Name, Employee_emailID, Employee_Password, Employee_Address, Employee_phoneNumb, Employee_Salary, ManagerID, Department_ID, Designation, Access_level));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Employee> getAllEmployees(){

        return new HashMap<>(modelEmployees);
    }

    public Employee getEmployee(String id){
        return modelEmployees.get(id);
    }

    public boolean insert(Employee e ){
    String query=DBQueriesConstant.insertEmployeeQuery+" values('"+e.getEmployee_ID()+"','"
            +e.getEmployee_Name()+"','"+e.getEmployee_emailID()+"','"+e.getEmployee_Password()+"','"+e.getEmployee_Address()+"','"+e.getEmployee_phoneNumb()+"','"
            +e.getEmployee_Salary()+"','"+e.getManagerID()+"','"+e.getDepartment_ID()+"','"+e.getDesignation()+"','"+e.getAccess_level()+"')";
    boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
    if(insertStatus){
        modelEmployees.put(e.getEmployee_ID(),e);
    }
    return insertStatus;
    }

    
    public Boolean update(String query,Employee employee){
        boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
        if (insertStatus) {
            modelEmployees.put(employee.getEmployee_ID(), employee);
        }
        return insertStatus;
    }



}
