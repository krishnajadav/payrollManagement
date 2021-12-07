package com.asdc.payroll_management.TaxCalculation;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaxCalculationController {

	@RequestMapping(value = "/TaxCalculation/generate", method = RequestMethod.GET)
	public String generateTaxCalculation(HttpServletRequest request) {

		String[] userInfo = request.getSession().getAttribute("userInfo").toString().split("#");
		TaxCalculation TC = new TaxCalculation(userInfo[0], 0.0, 0.0);
		String salaryCal = new TaxCalculationDAO().generateTaxCalculation(TC);
		if (salaryCal == null) {
			salaryCal = "0";
		}
		Double salary = Double.parseDouble(salaryCal);
		TC.setSalary(salary);
		TC.setSalaryWithoutTax(salary);
		return TC.getSalaryWithoutTax() + "#" + TC.getSalary();
	}

	@RequestMapping("/TaxCalculation")
	public ModelAndView TaxCalculation(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		if (request.getSession().getAttribute("userInfo") != null) {
			mv.setViewName("TaxCalculation");
			return mv;
		} else {
			return new ModelAndView("redirect:/LoginSignup");
		}
	}
}
