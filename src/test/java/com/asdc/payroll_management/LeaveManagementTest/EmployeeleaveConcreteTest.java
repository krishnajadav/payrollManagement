package com.asdc.payroll_management.LeaveManagementTest;
import com.asdc.payroll_management.LeaveManagement.BL.EmployeeleaveConcrete;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveEmployee;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveRequest;
import com.asdc.payroll_management.LeaveManagement.Model.LeaveType;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.assertj.core.error.ShouldBeSymbolicLink;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@SpringBootTest
public class EmployeeleaveConcreteTest {

    @Test
    public void testEmployeeLeaveConcreteExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.LeaveManagement.BL.EmployeeleaveConcrete");
            assertNotNull(C);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }

    @Test
    public void createLeaveRequesttest() {
        //Calculator classUnderTest = new Calculator();

        String  EMPID="1001";
        String EMPNAME = "Ali Shan";
        String ManagerID = "1002";

        EmployeeleaveConcrete classUnderTest = new EmployeeleaveConcrete(new LeaveEmployee(EMPID,EMPNAME,ManagerID));
        Date startDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();

        LeaveRequest testEmp = classUnderTest.createLeaveRequest(2,1,  startDate,"0",null);
        assertNotNull(testEmp);
        assertEquals("1001",testEmp.getEmployeeID());
        assertNotEquals("1002",testEmp.getEmployeeID());
    }

    @Test
    public void getDurartiontest() throws ParseException {
        //Calculator classUnderTest = new Calculator();

        String EMPID = "1001";
        String EMPNAME = "Ali Shan";
        String ManagerID = "1002";

        EmployeeleaveConcrete classUnderTest = new EmployeeleaveConcrete(new LeaveEmployee(EMPID, EMPNAME, ManagerID));
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

        String EMPID = "1001";
        String EMPNAME = "Ali Shan";
        String ManagerID = "1002";

        EmployeeleaveConcrete classUnderTest = new EmployeeleaveConcrete(new LeaveEmployee(EMPID, EMPNAME, ManagerID));
        Date startDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();
        Date endDate=classUnderTest.getEndDate(startDate,4);
        Date expectedEnddate = new GregorianCalendar(2021, Calendar.OCTOBER, 05).getTime();
        Date incorrectEnddate = new GregorianCalendar(2021, Calendar.OCTOBER, 10).getTime();

        assertEquals(expectedEnddate,endDate);
        assertNotEquals(incorrectEnddate,endDate);

    }

    @Test
    public void checkDateRangeTest(){

        String EMPID = "1001";
        String EMPNAME = "Ali Shan";
        String ManagerID = "1002";

        EmployeeleaveConcrete classUnderTest = new EmployeeleaveConcrete(new LeaveEmployee(EMPID, EMPNAME, ManagerID));
        Date startDate = new GregorianCalendar(2021, Calendar.OCTOBER, 01).getTime();

        LeaveRequest testEmp = classUnderTest.createLeaveRequest(2,1,  startDate,"1",null);
        LeaveType testType = new LeaveType(1,"Sick",2);
        Boolean validDuration = classUnderTest.checkDateRange(testEmp,testType);
        assertTrue(validDuration);
        assertFalse(!validDuration);

    }

    @Test
    public void checkEndDateandDurartionTest(){
        String EMPID = "1001";
        String EMPNAME = "Ali Shan";
        String ManagerID = "1002";

        EmployeeleaveConcrete classUnderTest = new EmployeeleaveConcrete(new LeaveEmployee(EMPID, EMPNAME, ManagerID));
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
