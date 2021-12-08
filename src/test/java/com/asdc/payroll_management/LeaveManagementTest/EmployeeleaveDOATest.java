package com.asdc.payroll_management.LeaveManagementTest;

import com.asdc.payroll_management.DataBaseCache.*;
import com.asdc.payroll_management.LeaveManagement.EmployeeLeavesDOA;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeleaveDOATest {
    private static EmployeeLeavesDOA classUnderTest = null;

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

        classUnderTest=new EmployeeLeavesDOA("1225");
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public void testEmployeeLeaveConcreteExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.LeaveManagement.EmployeeLeavesDOA");
            assertNotNull(C);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }



    @Test
    public void getDurartiontest() throws ParseException {
        //Calculator classUnderTest = new Calculator();

        Date startDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();
        Date endDate = new GregorianCalendar(2021, Calendar.OCTOBER, 05).getTime();

        int dur =classUnderTest.getDurartion(startDate,endDate);
        // System.out.println(dur);
        assertEquals(4,dur);
        assertNotEquals(10,dur);
    }

    @Test
    public void getEndDateTest()  {
        //Calculator classUnderTest = new Calculator();


        Date startDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();
        Date endDate=classUnderTest.getEndDate(startDate,4);
        Date expectedEnddate = new GregorianCalendar(2021, Calendar.OCTOBER, 05).getTime();
        Date incorrectEnddate = new GregorianCalendar(2021, Calendar.OCTOBER, 10).getTime();

        assertEquals(expectedEnddate,endDate);
        assertNotEquals(incorrectEnddate,endDate);

    }

    @Test
    public void checkDateRangeTest(){

        Boolean validDuration = classUnderTest.checkDateRange(3,2);
        assertTrue(validDuration);
        assertFalse(!validDuration);

    }

    @Test
    public void checkEndDateandDurartionTest(){

        Date endDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();

        Boolean validInput = classUnderTest.checkEndDateandDurartion(null,1);
        Boolean validInput1 = classUnderTest.checkEndDateandDurartion(endDate,-1);
        Boolean validInput2 = classUnderTest.checkEndDateandDurartion(null,-1);
        Boolean validInput3 = classUnderTest.checkEndDateandDurartion(endDate,2);

        assertTrue(validInput);
        assertTrue(validInput1);
        assertTrue(validInput3);
        assertFalse(validInput2);
    }




    }
