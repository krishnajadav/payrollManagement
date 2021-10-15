package com.asdc.payroll_management;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayrollManagementController {


    @GetMapping("/{name}")
    public String helloWorld(@PathVariable String name, @RequestParam(name = "last") String lastName) {
        return "Hey "+name+ " "+ lastName +"!";
    }


}
