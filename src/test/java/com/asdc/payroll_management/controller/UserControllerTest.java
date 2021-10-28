package com.asdc.payroll_management.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class UserControllerTest {

    @Test
    public void TestUserControllerExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.controller.UserController");
            assertNotNull(C);
        }catch (Exception e){
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }
	
}
