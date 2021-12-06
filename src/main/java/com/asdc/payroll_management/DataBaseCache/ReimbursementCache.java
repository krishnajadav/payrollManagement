package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;

public class ReimbursementCache {

    private static HashMap<String, Reimbursement> modelReimbursement = new HashMap<>();
    private static ReimbursementCache reimbursementCache=null;

    public static ReimbursementCache getInstance(){
        if(reimbursementCache==null){
            reimbursementCache=new ReimbursementCache();
        }
        return reimbursementCache;
    }

    private ReimbursementCache() {
        load();
    }


    private void load() {

        try {
            ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.allReimbursementQuery);
            while (rs.next()) {
                String RT_ID = rs.getString("RT_ID");
                String Reimbursement_Type = rs.getString("Reimbursement_Type");
                int Reimbursement_Limit = rs.getInt("Reimbursement_Limit");

                modelReimbursement.put(RT_ID, new Reimbursement(RT_ID, Reimbursement_Type, Reimbursement_Limit));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Reimbursement> getAllReimbursements(){

        return new HashMap<>(modelReimbursement);
    }
    
    public String getReimbursement_Type(String RT_ID)
    {
    	return modelReimbursement.get(RT_ID).getReimbursement_Type();
    }
    
    public int getReimbursement_Limit(String RT_ID)
    {
    	return modelReimbursement.get(RT_ID).getReimbursement_Limit();
    }

}
