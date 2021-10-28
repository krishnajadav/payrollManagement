package com.asdc.payroll_management.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void TestUserServiceExist() {
        //Calculator classUnderTest = new Calculator();
        try {
            Class C = Class.forName("com.asdc.payroll_management.service.UserService");
            assertNotNull(C);
        }catch (Exception e){
            // System.out.println(e.getMessage());
            assertNull(e);
        }
    }
}
