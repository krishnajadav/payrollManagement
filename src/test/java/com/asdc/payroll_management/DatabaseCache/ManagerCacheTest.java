package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.ManagerCache;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ManagerCacheTest {


    @Test
    public void testCacheExist() {
        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.ManagerCache");
            assertNotNull(C);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }

    @Test
    public void testManagerSingleton(){
        ManagerCache managerCache1=ManagerCache.getInstance();
        ManagerCache managerCache2=ManagerCache.getInstance();
        assertEquals(managerCache1,managerCache2);
    }

    @Test
    public void testLoaddata()  {
        try{
            DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
            try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
                mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
                ResultSet rs = mock(ResultSet.class);
                when(databaseConnection.getData(DBQueriesConstant.allManagersQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("Manager_ID")).thenReturn("1");
                when(rs.getString("Manager_Name")).thenReturn("Ali");
                when(rs.getString("Manager_EmailID")).thenReturn("ali@mail.com");
                when(rs.getString("Manager_Password")).thenReturn("SickLeave");
                when(rs.getString("Manager_Salary")).thenReturn("50000");
                ManagerCache managerCache = ManagerCache.getInstance();

                assertEquals("SickLeave",managerCache.get().get("1").getManager_Password());
                assertNotEquals("30000",managerCache.get().get("1").getManager_Salary());
            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }


}
