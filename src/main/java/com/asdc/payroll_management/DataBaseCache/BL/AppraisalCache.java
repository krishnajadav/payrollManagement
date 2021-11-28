package com.asdc.payroll_management.DataBaseCache.BL;

import com.asdc.payroll_management.DataBaseCache.Model.Appraisal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AppraisalCache {
    private static HashMap<String, Appraisal> modelAppraisals = new HashMap<>();
    private static AppraisalCache appraisalFactory=null;

    public static AppraisalCache getInstance(){
        if(appraisalFactory==null){
            appraisalFactory=new AppraisalCache();
        }
        return appraisalFactory;
    }

    private AppraisalCache() {
        load();
    }

    private void load() {

        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allAppraisalQuery);
            while (rs.next()) {
                String employee_ID = rs.getString("employee_ID");
                String manager_ID = rs.getString("manager_ID");
                String employee_rating = rs.getString("employee_rating");
                String eployee_comments = rs.getString("eployee_comments");
                String manager_rating = rs.getString("manager_rating");
                String manager_comments = rs.getString("manager_comments");
                String employee_projects = rs.getString("employee_projects");
                String technologies_learnt = rs.getString("technologies_learnt");
                String final_rating = rs.getString("final_rating");
                String communication_rating = rs.getString("communication_rating");
                String projects_rating = rs.getString("projects_rating");

                Appraisal tempAppraisal = new Appraisal(employee_ID, manager_ID, employee_rating, eployee_comments, manager_rating, manager_comments, employee_projects, technologies_learnt, final_rating, communication_rating, projects_rating);
                // tempLeaveRequest.setIsAccepted(isAccepted);
                modelAppraisals.put(employee_ID, tempAppraisal);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

        public HashMap<String, Appraisal> get () {

            return modelAppraisals;
        }


    public  Appraisal getOne(String id){

        return modelAppraisals.get(id);
    }


    public boolean insert(Appraisal e ) throws SQLException, ClassNotFoundException {
        String query=DBQueriesConstant.insertAppraisalQuery+" values('"+e.getEmployee_ID()+"','"
                +e.getManager_ID()+"','"+e.getEmployee_rating()+"','"+e.getEployee_comments()+"','"+e.getManager_rating()+"','"+e.getManager_comments()+"','"+e.getEmployee_projects()+"','"+e.getTechnologies_learnt()+"','"+e.getFinal_rating()+"','"+e.getCommunication_rating()+"','"+e.getProjects_rating()+"')";
        Boolean insertStatus= DatabaseConnection.getInstance().insertData(query);
        if(insertStatus){

            modelAppraisals.put(e.getEmployee_ID(),e);
        }
        return insertStatus;
    }



}
