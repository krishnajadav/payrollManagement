package com.asdc.payroll_management.UserAuthentication;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserAuthenticationController {

	    private UserAuthenticationConcreteFactory UACF=new UserAuthenticationConcreteFactory();
	    
	    @RequestMapping(value = "/UserAuthentication/check",method = RequestMethod.POST)
	    public String checkUserAuthentication(@RequestBody UserAuthentication ua) {	
	    	if(ua.validate())
	    	{
		    	return UACF.checkUserAuthentication(ua);
	    	}
	    	else
	    	{
	    		return "Empty";
	    	}
	    }
	    
		@RequestMapping("/LoginSignup")
		public ModelAndView LoginSignup() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("LoginSignup");
			return mv;
		}
	       
}
