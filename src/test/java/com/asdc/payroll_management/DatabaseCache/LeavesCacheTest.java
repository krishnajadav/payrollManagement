package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.BL.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.BL.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.BL.LeavesCache;
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
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.BL.LeavesCache");
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
                when(databaseConnection.getData(DBQueriesConstant.allLeavesQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("Leaves_ID")).thenReturn("1");
                when(rs.getString("Leaves_Name")).thenReturn("Sick Leave");
                when(rs.getString("Leaves_DuartionLimit")).thenReturn("2");

                LeavesCache leavesFactory = LeavesCache.getInstance();
                assertEquals("Sick Leave",leavesFactory.get().get("1").getLeaves_Name());
                assertNotEquals("3",leavesFactory.get().get("1").getLeaves_DuartionLimit());

            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }


}