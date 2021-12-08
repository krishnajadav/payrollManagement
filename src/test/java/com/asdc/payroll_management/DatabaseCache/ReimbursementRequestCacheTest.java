package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache;
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
            Class C = Class.forName("com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache");
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
                when(databaseConnection.getData(DBQueriesConstant.ALL_REIMBURSEMENT_REQUESTS_QUERY)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(false);
                when(rs.getString("RR_ID")).thenReturn("1");
                when(rs.getString("RR_EmployeeID")).thenReturn("26118");
                when(rs.getString("RR_TypeID")).thenReturn("3");
                when(rs.getString("RR_Note")).thenReturn("SickLeave");
                when(rs.getString("RR_Amount")).thenReturn("50000");

                when(rs.getString("RR_Date")).thenReturn("2021-11-01");
                when(rs.getString("isAccepted")).thenReturn("0");

                ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();

                assertEquals("3",reimbursementRequestCache.getAllReimbursementRequest().get("1").getRRTypeID());
                assertNotEquals("30000",reimbursementRequestCache.getAllReimbursementRequest().get("1").getRrAmount());
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
        String query=DBQueriesConstant.INSERT_REIMBURSEMENT_REQUESTS_QUERY +" values('"+reimbursementRequest.getRrId()+"','"
                +reimbursementRequest.getRrEmployeeid()+"','"+reimbursementRequest.getRRTypeID()+"','"+reimbursementRequest.getRrNote()+"','"+reimbursementRequest.getRrAmount()+"','"+reimbursementRequest.getRrDate()+"','"+reimbursementRequest.getIsAccepted()+"')";
        try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            reimbursementRequestCache.insert(reimbursementRequest);
            HashMap<String,ReimbursementRequest> reimbursementRequestHashMap=reimbursementRequestCache.getAllReimbursementRequest();
            ReimbursementRequest request = reimbursementRequestHashMap.get("2");
            assertEquals("26119",request.getRrEmployeeid());
            assertEquals("0",request.getIsAccepted());
            assertNotEquals("2021-12-01",request.getRrDate());


        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
