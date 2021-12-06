package com.asdc.payroll_management.TaxCalculation;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.asdc.payroll_management.DataBaseCache.SalaryHist;
import com.asdc.payroll_management.DataBaseCache.SalaryHistCache;

public class TaxCalculationDAO implements ITaxCalculationDAO {
	
	@Override
    public String generateTaxCalculation(TaxCalculation tc)
    {	
		try {		
	        SalaryHistCache salaryHistCache = SalaryHistCache.getInstance();
	        HashMap<String, SalaryHist> salaryHashMap = salaryHistCache.getAllSalaries();
	        Double totalSalary=0.0;
	        for (Map.Entry mapElement : salaryHashMap.entrySet()) {
	        	SalaryHist salaryTemp = (SalaryHist) mapElement.getValue();          	        	
	            if (salaryTemp.getEmployee_ID().equals(tc.getUserID()))
	            {
		        	LocalDate startDate = LocalDate.parse(salaryTemp.getStart_Date());
		        	LocalDate currentDate = LocalDate.now();
		        	LocalDate yearAgoDate = currentDate.minusYears( 1 );
		        	boolean isWithinYearAgo = ((startDate.isAfter( yearAgoDate)) & ( startDate.isBefore(currentDate)));
		        	if(isWithinYearAgo)
		        	{
		        		totalSalary=totalSalary+ Double.parseDouble(salaryTemp.getSalary());
		        	}	            	
	            }
	        }	        
	        return totalSalary.toString();											
		}
		catch (Exception e) {
			return e.getMessage();
		}
    }
	
}
