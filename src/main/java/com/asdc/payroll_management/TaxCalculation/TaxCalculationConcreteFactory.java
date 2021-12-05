package com.asdc.payroll_management.TaxCalculation;

public class TaxCalculationConcreteFactory extends TaxCalculationAbstractFactory  {
	
public String generateTaxCalculation(TaxCalculation tc) {	
	TaxCalculationDAO TCDAO=new TaxCalculationDAO();  
    return TCDAO.generateTaxCalculation(tc);
}
 
}
