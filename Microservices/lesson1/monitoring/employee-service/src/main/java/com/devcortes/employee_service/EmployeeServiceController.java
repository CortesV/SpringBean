package com.devcortes.employee_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class EmployeeServiceController {

    private static final Map<Integer, Employee> employeeData = new HashMap<>();

    {
        employeeData.put(111, new Employee(111, "Employee1"));
        employeeData.put(222, new Employee(222, "Employee2"));
    }

    @GetMapping(value = "/find-employee-details/{employeeId}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable int employeeId) {
        System.out.println("Getting Employee details for " + employeeId);

        Employee employee = employeeData.get(employeeId);
        if (employee == null) {

            employee = new Employee(0, "N/A");
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/find-employee-details")
    public ResponseEntity<Map<Integer, Employee>> getEmployeeDetails() {
        return new ResponseEntity<>(employeeData, HttpStatus.OK);
    }
}
