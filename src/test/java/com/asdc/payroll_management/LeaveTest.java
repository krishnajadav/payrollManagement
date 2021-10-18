package com.asdc.payroll_management;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class LeaveTest {
	
    @Test
    public void TestLeaveExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.Leave");
            assertNotNull(C);
        }catch (Exception e){
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }

}
