package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeCacheTest {

    @Test
    public void testEmployeeFactoryExist() {

        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.EmployeeCache");
            assertNotNull(C);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }


    @Test
    public void testLoaddata()  {
        try{

            DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
            try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
                mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
                ResultSet rs = mock(ResultSet.class);
                when(databaseConnection.getData(DBQueriesConstant.allEmployeesQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
                when(rs.getString("Employee_ID")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("Employee_Name")).thenReturn("jaswanth").thenReturn("Ali");
                when(rs.getString("Employee_emailID")).thenReturn("jaswanth@gmail.com").thenReturn("ali@gmail.com");
                when(rs.getString("Employee_Password")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("Employee_Address")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("Employee_phoneNumb")).thenReturn("7826410377").thenReturn("7826410388");
                when(rs.getString("Employee_Salary")).thenReturn("2611900").thenReturn("2611800");
                when(rs.getString("ManagerID")).thenReturn("26120").thenReturn("26117");
                when(rs.getString("Department_ID")).thenReturn("26").thenReturn("26");
                when(rs.getString("Designation")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("Access_level")).thenReturn("Senior Manager").thenReturn("Manager");
                EmployeeCache employeeFactory = EmployeeCache.getInstance();
                assertEquals(2,employeeFactory.get().size());
            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public void testInsertData()  {
        Employee testEmp = new Employee("26118","Ali","ali@gmail.com","abc","124 street","12345","12345","1234","1","1","Manager");
        EmployeeCache employeeFactory = EmployeeCache.getInstance();
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        String query=DBQueriesConstant.insertEmployeeQuery+" values('"+testEmp.getEmployee_ID()+"','"
                +testEmp.getEmployee_Name()+"','"+testEmp.getEmployee_emailID()+"','"+testEmp.getEmployee_Password()+"','"+testEmp.getEmployee_Address()+"','"+testEmp.getEmployee_phoneNumb()+"','"
                +testEmp.getEmployee_Salary()+"','"+testEmp.getManagerID()+"','"+testEmp.getDepartment_ID()+"','"+testEmp.getDesignation()+"','"+testEmp.getAccess_level()+"')";
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            employeeFactory.insert(testEmp);
            HashMap<String,Employee> testEmployees=employeeFactory.get();
            Employee newEmp = testEmployees.get("26118");
            assertEquals("Ali",newEmp.getEmployee_Name());
            assertEquals("abc",newEmp.getEmployee_Password());
            assertNotEquals("Jas",newEmp.getEmployee_Name());
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }


}
