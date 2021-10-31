package com.asdc.payroll_management.HRRegistration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HRConcreteFactory extends HRAbstractFactory {

@Override
    public List<HR> getAllHRs() {
	
	    HRDAO MngDAO=new HRDAO();
        List<HR> HRs = new ArrayList<HR>();
        
		try {
			HRs = MngDAO.getAllHRs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return HRs;
    }	

@Override
public String saveHR(HR mng) {

	HRDAO MngDAO=new HRDAO();  
    return MngDAO.saveHR(mng);

}

    
}
