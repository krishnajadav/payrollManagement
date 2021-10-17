package com.asdc.payroll_management;

 import org.junit.jupiter.api.Test;
 import org.springframework.boot.test.context.SpringBootTest;
 import static org.junit.Assert.*;

 @SpringBootTest
class PayrollManagementApplicationTests {

   @Test
    void contextLoads() {
    }

  @Test
  public void testEmployeeExist() {
   //Calculator classUnderTest = new Calculator();
   try {
    Class C = Class.forName("com.asdc.payroll_management.Employee");
    assertNotNull(C);
   }catch (Exception e){
    // System.out.println(e.getMessage());
    assertNull(e);
   }
  }

}
