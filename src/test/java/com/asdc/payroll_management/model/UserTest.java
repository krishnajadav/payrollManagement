package com.asdc.payroll_management.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void TestUserClassExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.model.User");
            assertNotNull(C);
        }catch (Exception e){
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }
	
}
