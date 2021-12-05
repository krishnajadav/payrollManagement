package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.LeavesCache;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LeavesCacheTest {

    @Test
    public void testLeavesFactoryExist() {

        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.LeavesCache");
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
                when(databaseConnection.getData(DBQueriesConstant.ALL_LEAVES_QUERY)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("Leaves_ID")).thenReturn("1");
                when(rs.getString("Leaves_Name")).thenReturn("Sick Leave");
                when(rs.getString("Leaves_DuartionLimit")).thenReturn("2");

                LeavesCache leavesFactory = LeavesCache.getInstance();
                assertEquals("Sick Leave",leavesFactory.getAllLeaveTypes().get("1").getLeavesName());
                assertNotEquals("3",leavesFactory.getAllLeaveTypes().get("1").getLeavesDuartionLimit());

            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }


}
