package com.example.demo.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.demo.model.Employee;

public interface EmployeeService {
    
	Page<Employee> getAllEmployees(int pageNumber, int pageSize, String sortBy);
    
    UUID saveEmployee(Employee employee);
    
    void deleteEmployee(UUID id);
    
    Employee updateEmployee(Employee employee);
    
    Employee getNthLevelManager(UUID employeeId, int level);
}
