//package com.asdc.payroll_management.ReimbursementManagementTest;
//
//import com.asdc.payroll_management.DataBaseCache.*;
//import com.asdc.payroll_management.ReimbursementManagement.EmployeeReimbursementDOA;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.ResultSet;
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//public class EmployeeReimbursementDOATest {
//	private static EmployeeReimbursementDOA testClass = null;
//
//    @BeforeAll
//    public static void init(){
//        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
//        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
//            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
//            ResultSet rs = mock(ResultSet.class);
//            when(databaseConnection.getData(DBQueriesConstant.allEmployeesQuery)).thenReturn(rs);
//            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
//            when(rs.getString("Employee_ID")).thenReturn("1225").thenReturn("26118");
//            when(rs.getString("Employee_Name")).thenReturn("jaswanth").thenReturn("Ali");
//            when(rs.getString("Employee_emailID")).thenReturn("jaswanth@gmail.com").thenReturn("ali@gmail.com");
//            when(rs.getString("Employee_Password")).thenReturn("26119").thenReturn("26118");
//            when(rs.getString("Employee_Address")).thenReturn("26119").thenReturn("26118");
//            when(rs.getString("Employee_phoneNumb")).thenReturn("7826410377").thenReturn("7826410388");
//            when(rs.getString("Employee_Salary")).thenReturn("2611900").thenReturn("2611800");
//            when(rs.getString("ManagerID")).thenReturn("26120").thenReturn("26117");
//            when(rs.getString("Department_ID")).thenReturn("26").thenReturn("26");
//			when(rs.getString("Designation")).thenReturn("26119").thenReturn("26118");
//            EmployeeCache employeeFactory = EmployeeCache.getInstance();
//
//        testClass=new EmployeeReimbursementDOA("1225");
//        }catch(Exception e){
//            e.printStackTrace();
//            fail("Tests failed due to exception");
//        }
//    }
//}
