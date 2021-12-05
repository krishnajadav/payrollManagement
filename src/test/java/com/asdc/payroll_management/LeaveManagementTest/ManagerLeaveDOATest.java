package com.asdc.payroll_management.LeaveManagementTest;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.LeaveManagement.ManagerLeavesDOA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ManagerLeaveDOATest {
    private static ManagerLeavesDOA classUnderTest = null;

    @Test
    public void testEmployeeFactoryExist() {

        try {
            Class C = Class.forName("com.asdc.payroll_management.LeaveManagement.ManagerLeavesDOA");
            assertNotNull(C);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }

    @BeforeAll
    public static void init(){
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            ResultSet rs = mock(ResultSet.class);
            when(databaseConnection.getData(DBQueriesConstant.All_EMPLOYEE_QUERY)).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            when(rs.getString("Employee_ID")).thenReturn("1225").thenReturn("26118");
            when(rs.getString("Employee_Name")).thenReturn("jaswanth").thenReturn("Ali");
            when(rs.getString("Employee_emailID")).thenReturn("jaswanth@gmail.com").thenReturn("ali@gmail.com");
            when(rs.getString("Employee_Password")).thenReturn("26119").thenReturn("26118");
            when(rs.getString("Employee_Address")).thenReturn("26119").thenReturn("26118");
            when(rs.getString("Employee_phoneNumb")).thenReturn("7826410377").thenReturn("7826410388");
            when(rs.getString("Employee_Salary")).thenReturn("2611900").thenReturn("2611800");
            when(rs.getString("ManagerID")).thenReturn("26120").thenReturn("26117");
            when(rs.getString("Department_ID")).thenReturn("26").thenReturn("26");
            when(rs.getString("Designation")).thenReturn("26119").thenReturn("26118");
            EmployeeCache employeeFactory = EmployeeCache.getInstance();

            classUnderTest=new ManagerLeavesDOA("1225");
            assertNotNull(classUnderTest);
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public static void gateALLStaffTest(){
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            ResultSet rs = mock(ResultSet.class);
            when(databaseConnection.getData(DBQueriesConstant.All_EMPLOYEE_QUERY)).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            when(rs.getString("Employee_ID")).thenReturn("1225").thenReturn("26118");
            when(rs.getString("Employee_Name")).thenReturn("jaswanth").thenReturn("Ali");
            when(rs.getString("Employee_emailID")).thenReturn("jaswanth@gmail.com").thenReturn("ali@gmail.com");
            when(rs.getString("Employee_Password")).thenReturn("26119").thenReturn("26118");
            when(rs.getString("Employee_Address")).thenReturn("26119").thenReturn("26118");
            when(rs.getString("Employee_phoneNumb")).thenReturn("7826410377").thenReturn("7826410388");
            when(rs.getString("Employee_Salary")).thenReturn("2611900").thenReturn("2611800");
            when(rs.getString("ManagerID")).thenReturn("26120").thenReturn("1225");
            when(rs.getString("Department_ID")).thenReturn("26").thenReturn("26");
            when(rs.getString("Designation")).thenReturn("26119").thenReturn("26118");
            EmployeeCache employeeFactory = EmployeeCache.getInstance();

            classUnderTest=new ManagerLeavesDOA("1225");

            List<Employee> employees = classUnderTest.getAllStaff();
            assertEquals("26120",employees.get(0));

            assertNotNull(classUnderTest);
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }

    }



}
