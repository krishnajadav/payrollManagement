package com.asdc.payroll_management.UserAuthentication;

public class UserAuthenticationConcreteFactory extends UserAuthenticationAbstractFactory {
	
@Override
public String checkUserAuthentication(UserAuthentication ua) {	
	UserAuthenticationDAO UADAO=new UserAuthenticationDAO();  
    return UADAO.checkUserAuthentication(ua);
}

    
}
