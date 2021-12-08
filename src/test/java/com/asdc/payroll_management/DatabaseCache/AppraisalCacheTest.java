package com.asdc.payroll_management.DatabaseCache;

import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.DBQueriesConstant;
import com.asdc.payroll_management.DataBaseCache.DatabaseConnection;
import com.asdc.payroll_management.DataBaseCache.Appraisal;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AppraisalCacheTest {

    @Test
    public void testEmployeeFactoryExist() {

        try {
            Class<?> C = Class.forName("com.asdc.payroll_management.DataBaseCache.AppraisalCache");
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
            try (MockedStatic<DatabaseConnection> mocked = mockStatic(DatabaseConnection.class)) {
                mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
                ResultSet rs = mock(ResultSet.class);
                when(databaseConnection.getData(DBQueriesConstant.ALL_APPRAISAL_QUERY)).thenReturn(rs);
                when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
                when(rs.getString("employee_ID")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("manager_ID")).thenReturn("26118").thenReturn("26120");
                when(rs.getString("employee_rating")).thenReturn("5").thenReturn("2");
                when(rs.getString("eployee_comments")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("manager_rating")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("manager_comments")).thenReturn("7826410377").thenReturn("7826410388");
                when(rs.getString("employee_projects")).thenReturn("2611900").thenReturn("2611800");
                when(rs.getString("technologies_learnt")).thenReturn("26120").thenReturn("26117");
                when(rs.getString("final_rating")).thenReturn("26").thenReturn("26");
                when(rs.getString("communication_rating")).thenReturn("26119").thenReturn("26118");
                when(rs.getString("projects_rating")).thenReturn("26119").thenReturn("26118");
                AppraisalCache appraisalFactory = AppraisalCache.getInstance();
                //assertNull(appraisalFactory.getIndividualAppraisals("26119").getManagerID());
                assertEquals("7826410388",appraisalFactory.getIndividualAppraisals("26118").getManagerComments());
                mocked.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertData() throws SQLException, ClassNotFoundException {
        Appraisal appraisal = new Appraisal("26120","125","ali@gmail.com","abc","124 street","12345","12345","1234","1","1","2");

        DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
        String query=DBQueriesConstant.INSERT_APPRAISAL_QUERY +" values('"+appraisal.getEmployeeID()+"','"
                +appraisal.getManagerID()+"','"+appraisal.getEmployeeRating()+"','"+appraisal.getEployeeComments()+"','"+appraisal.getManagerRating()+"','"+appraisal.getManagerComments()+"','"+appraisal.getEmployeeProjects()+"','"+appraisal.getTechnologiesLearnt()+"','"+appraisal.getFinalRating()+"','"+appraisal.getCommunicationRating()+"','"+appraisal.getProjectsRating()+"')";

        try (MockedStatic<DatabaseConnection> mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
            when(databaseConnection.insertData(query)).thenReturn(true);
            AppraisalCache appraisalFactory = AppraisalCache.getInstance();
            appraisalFactory.insert(appraisal);
            Appraisal testAppraisal=appraisalFactory.getIndividualAppraisals("26120");
            assertEquals("125",testAppraisal.getManagerID());
            assertEquals("abc",testAppraisal.getEployeeComments());
            assertNotEquals("Jas",testAppraisal.getManagerID());
            mocked.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
