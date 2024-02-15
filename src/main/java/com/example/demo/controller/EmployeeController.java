package com.example.demo.controller;




import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getemployees")
    public ResponseEntity<Page<Employee>> getEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "employeeName") String sort) {

        Page<Employee> employees = employeeService.getAllEmployees(page, size, sort);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/saveemployee")
    public UUID saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/updateemployee/{id}")
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        if (!id.equals(employee.getId())) {
            throw new IllegalArgumentException("Path variable ID does not match employee ID");
        }

        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/deleteemployee")
    public String deleteEmployee(@RequestParam UUID id) {
        employeeService.deleteEmployee(id);
        return "Employee with ID " + id + " deleted successfully.";
    }
    
    @GetMapping("/getnthlevelmanager/{employeeId}/{level}")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable UUID employeeId,
            @PathVariable int level) {

        Employee manager = employeeService.getNthLevelManager(employeeId, level);
        if (manager != null) {
            return ResponseEntity.ok(manager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}