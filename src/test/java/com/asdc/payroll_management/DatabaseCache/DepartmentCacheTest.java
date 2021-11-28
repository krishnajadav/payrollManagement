package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.DepartmentCache;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DepartmentCacheTest {

    @Test
    public void testEmployeeFactoryExist() {

        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.DepartmentCache");
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
                when(databaseConnection.getData(DBQueriesConstant.allDepartmentQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("Department_ID")).thenReturn("26118");
                when(rs.getString("Department_Name")).thenReturn("Finance");
                when(rs.getString("HR_ID")).thenReturn("22");

                DepartmentCache departmentFactory = DepartmentCache.getInstance();
                assertEquals("Finance",departmentFactory.get().get("26118").getDepartment_Name());
                assertNotEquals("Marketing",departmentFactory.get().get("26118").getDepartment_Name());

            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

}
