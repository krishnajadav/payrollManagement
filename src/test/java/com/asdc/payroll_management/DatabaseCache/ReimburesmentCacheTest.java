package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.ReimbursementCache;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReimburesmentCacheTest {
    @Test
    public void testCacheExist() {
        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.ReimbursementCache");
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
                when(databaseConnection.getData(DBQueriesConstant.ALL_REIMBURSEMENT_QUERY)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("RT_ID")).thenReturn("1");
                when(rs.getString("Reimbursement_Type")).thenReturn("Food");
                when(rs.getString("Reimbursement_Limit")).thenReturn("500");
                ReimbursementCache reimbursementCache = ReimbursementCache.getInstance();

                assertEquals("Food",reimbursementCache.getAllReimbursements().get("1").getReimbursementType());
                assertNotEquals("30000",reimbursementCache.getAllReimbursements().get("1").getReimbursementLimit());
            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }
}
