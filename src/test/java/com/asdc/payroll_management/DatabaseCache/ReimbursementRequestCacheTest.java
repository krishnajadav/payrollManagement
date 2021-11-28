package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.BL.*;
import com.asdc.payroll_management.DataBaseCache.Model.Employee;
import com.asdc.payroll_management.DataBaseCache.Model.ReimbursementRequest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReimbursementRequestCacheTest {
    @Test
    public void testCacheExist() {
        try {
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.BL.ReimbursementRequestCache");
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
                when(databaseConnection.getData(DBQueriesConstant.allReimbursementRequestsQuery)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("RR_ID")).thenReturn("1");
                when(rs.getString("RR_EmployeeID")).thenReturn("26118");
                when(rs.getString("RR_TypeID")).thenReturn("3");
                when(rs.getString("RR_Note")).thenReturn("SickLeave");
                when(rs.getString("RR_Amount")).thenReturn("50000");

                when(rs.getString("RR_Date")).thenReturn("2021-11-01");
                when(rs.getString("isAccepted")).thenReturn("0");

                ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();

                assertEquals("3",reimbursementRequestCache.get().get("1").getRR_TypeID());
                assertNotEquals("30000",reimbursementRequestCache.get().get("1").getRR_Amount());
            }
        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

    @Test
    public void testInsertData()  {
        ReimbursementRequest reimbursementRequest = new ReimbursementRequest("2","26119","2","abc","124","2021-11-01","0");
//        HashMap<String,Employee> testEmployees=new HashMap<>();
//        testEmployees.put("26118",new Employee("jaswanth","jas@gmail.com","abc","124 street","12345","12345","1234","1","1","1"));
        ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();
        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        String query=DBQueriesConstant.insertReimbursementRequestsQuery+" values('"+reimbursementRequest.getRR_ID()+"','"
                +reimbursementRequest.getRR_EmployeeID()+"','"+reimbursementRequest.getRR_TypeID()+"','"+reimbursementRequest.getRR_Note()+"','"+reimbursementRequest.getRR_Amount()+"','"+reimbursementRequest.getRR_Date()+"','"+reimbursementRequest.getIsAccepted()+"')";
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            reimbursementRequestCache.insert(reimbursementRequest);
            HashMap<String,ReimbursementRequest> reimbursementRequestHashMap=reimbursementRequestCache.get();
            ReimbursementRequest request = reimbursementRequestHashMap.get("2");
            assertEquals("26119",request.getRR_EmployeeID());
            assertEquals("0",request.getIsAccepted());
            assertNotEquals("2021-12-01",request.getRR_Date());


        }catch(Exception e){
            e.printStackTrace();
            fail("Tests failed due to exception");
        }
    }

}
