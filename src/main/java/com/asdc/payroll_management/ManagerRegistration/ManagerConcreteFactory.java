package com.asdc.payroll_management.ManagerRegistration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerConcreteFactory extends ManagerAbstractFactory {

@Override
    public List<Manager> getAllManagers() {
	
	    ManagerDAO MngDAO=new ManagerDAO();
        List<Manager> Managers = new ArrayList<Manager>();
        
		try {
			Managers = MngDAO.getAllManagers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return Managers;
    }	

@Override
public String saveManager(Manager mng) {

	ManagerDAO MngDAO=new ManagerDAO();  
    return MngDAO.saveManager(mng);

}

    
}
