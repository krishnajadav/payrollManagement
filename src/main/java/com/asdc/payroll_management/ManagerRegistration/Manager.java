package com.asdc.payroll_management.ManagerRegistration;

public class Manager {

	private String Manager_Name; 
	private String Manager_EmailID;
	private String Manager_Password;
	private int Manager_ID;
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String manager_Name, String manager_EmailID, String manager_Password, int manager_ID) {
		super();
		Manager_Name = manager_Name;
		Manager_EmailID = manager_EmailID;
		Manager_Password = manager_Password;
		Manager_ID = manager_ID;
	}
	public String getManager_Name() {
		return Manager_Name;
	}
	public void setManager_Name(String manager_Name) {
		Manager_Name = manager_Name;
	}
	public String getManager_EmailID() {
		return Manager_EmailID;
	}
	public void setManager_EmailID(String manager_EmailID) {
		Manager_EmailID = manager_EmailID;
	}
	public String getManager_Password() {
		return Manager_Password;
	}
	public void setManager_Password(String manager_Password) {
		Manager_Password = manager_Password;
	}
	public int getManager_ID() {
		return Manager_ID;
	}
	public void setManager_ID(int manager_ID) {
		Manager_ID = manager_ID;
	} 	
	
	public boolean validate()
	{
	   if(Manager_Name=="" || Manager_EmailID==""||Manager_Password=="")
	   {
		 return false;   
	   }
	   else
	   {
		return true;   
	   }
	}
}
