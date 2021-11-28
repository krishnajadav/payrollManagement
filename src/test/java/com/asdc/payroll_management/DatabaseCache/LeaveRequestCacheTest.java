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
                when(databaseConnection.getData(DBQueriesConstant.allLeaveRequestsQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("LR_ID")).thenReturn("2");
                when(rs.getString("LR_EmployeeID")).thenReturn("26118");
                when(rs.getString("LR_Duration")).thenReturn("22");
                when(rs.getString("LR_Type")).thenReturn("2");
                when(rs.getString("isAccepted")).thenReturn("Finance");
                when(rs.getString("Leave_Request_Date")).thenReturn("22");
                when(rs.getString("Leave_End_Date")).thenReturn("22");
                LeaveRequestCache leaveRequestFactory =  LeaveRequestCache.getInstance();

                assertEquals("26118",leaveRequestFactory.get().get("2").getLR_EmployeeID());
                assertNotEquals("5",leaveRequestFactory.get().get("2").getLR_Type());

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
        String query=DBQueriesConstant.insertLeaveRequestQuery+" values("+e.getLR_EmployeeID()+"','"
                +e.getLR_Duration()+","+e.getLR_Type()+","+e.getLeave_Request_Date()+","+e.getLeave_End_Date()+")";
        //Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            leaveRequestFactory.insert(e);
            LeaveRequest newEmp=leaveRequestFactory.getOne("2");

            assertEquals("26119",newEmp.getLR_EmployeeID());
            assertEquals("1",newEmp.getLR_Type());
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
            when(databaseConnection.updateData(DBQueriesConstant.updateLeaveRequestTrueQuery+"\""+e.getLR_ID()+"\";")).thenReturn(true);
            when(databaseConnection.updateData(DBQueriesConstant.updateLeaveRequestFalseQuery+"\""+e.getLR_ID()+"\";")).thenReturn(true);
            leaveRequestFactory.updateLeaveTrue(e);
            LeaveRequest newLR = leaveRequestFactory.getOne("2");
            assertEquals("1",newLR.getIsAccepted());

            leaveRequestFactory.updateLeaveFalse(e);
            LeaveRequest newLR2 = leaveRequestFactory.getOne("2");
            assertEquals("0",newLR2.getIsAccepted());

        }catch(Exception ex){
            ex.printStackTrace();
            fail("Tests failed due to exception");
        }

    }


}
