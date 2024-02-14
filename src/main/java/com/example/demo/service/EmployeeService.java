package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Employee;

public interface EmployeeService {
    
    List<Employee> getEmployees();
    
    UUID saveEmployee(Employee employee);
    
    void deleteEmployee(UUID id);
    
    Employee updateEmployee(Employee employee);
}
