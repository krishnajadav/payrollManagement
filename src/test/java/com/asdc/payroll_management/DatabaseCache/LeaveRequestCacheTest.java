package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.LeaveRequest;
import com.asdc.payroll_management.DataBaseCache.LeaveRequestCache;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class LeaveRequestCacheTest {
    @Test
    public void testEmployeeFactoryExist() {

        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.LeaveRequestCache");
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
                when(databaseConnection.getData(DBQueriesConstant.ALL_LEAVE_REQUESTS_QUERY)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("LR_ID")).thenReturn("2");
                when(rs.getString("LR_EmployeeID")).thenReturn("26118");
                when(rs.getString("LR_Duration")).thenReturn("22");
                when(rs.getString("LR_Type")).thenReturn("2");
                when(rs.getString("isAccepted")).thenReturn("Finance");
                when(rs.getString("Leave_Request_Date")).thenReturn("22");
                when(rs.getString("Leave_End_Date")).thenReturn("22");
                LeaveRequestCache leaveRequestFactory =  LeaveRequestCache.getInstance();

                assertEquals("26118",leaveRequestFactory.getAllLeaves().get("2").getLrEmployeeid());
                assertNotEquals("5",leaveRequestFactory.getAllLeaves().get("2").getLrType());

            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public void testInsertData()  {
        LeaveRequest e = new LeaveRequest("2","26119","2","1","0","2021-01-01","2021-01-02");
        LeaveRequestCache leaveRequestFactory = LeaveRequestCache.getInstance();
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        String query=DBQueriesConstant.INSERT_LEAVE_REQUEST_QUERY +" values("+e.getLrEmployeeid()+"','"
                +e.getLrDuration()+","+e.getLrType()+","+e.getLeaveRequestDate()+","+e.getLeaveEndDate()+")";
        //Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            leaveRequestFactory.insert(e);
            LeaveRequest newEmp=leaveRequestFactory.getOneLeave("2");

            assertEquals("26119",newEmp.getLrEmployeeid());
            assertEquals("1",newEmp.getLrType());
            assertNotEquals("Jas",newEmp.getIsAccepted());


        }catch(Exception ex){
            ex.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public void testUpdateData()  {
        LeaveRequest e = new LeaveRequest("2","26119","2","1","0","2021-01-01","2021-01-02");
        LeaveRequestCache leaveRequestFactory =LeaveRequestCache.getInstance();
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.updateData(DBQueriesConstant.UPDATE_LEAVE_REQUEST_TRUE_QUERY +"\""+e.getLrId()+"\";")).thenReturn(true);
            when(databaseConnection.updateData(DBQueriesConstant.UPDATE_LEAVE_REQUEST_FALSE_QUERY +"\""+e.getLrId()+"\";")).thenReturn(true);
            leaveRequestFactory.updateLeaveTrue(e);
            LeaveRequest newLR = leaveRequestFactory.getOneLeave("2");
            assertEquals("1",newLR.getIsAccepted());

            leaveRequestFactory.updateLeaveFalse(e);
            LeaveRequest newLR2 = leaveRequestFactory.getOneLeave("2");
            assertEquals("0",newLR2.getIsAccepted());

        }catch(Exception ex){
            ex.printStackTrace();
            fail("Tests failed due to exception");
        }

    }


}
