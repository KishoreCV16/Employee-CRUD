package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    
    @Override
    public UUID saveEmployee(Employee employee) {
    	if (employeeRepository.count() == 0) {
            // This is the first employee, you can handle the reportsTo field here
            employee.setReportsTo(null); // or set to a default value
        } else {
            // Check if the reportsTo ID exists
            UUID reportsToId = employee.getReportsTo();
            if (reportsToId != null) {
                Optional<Employee> reportsToEmployeeOptional = employeeRepository.findById(reportsToId);
                if (!reportsToEmployeeOptional.isPresent()) {
                    throw new RuntimeException("Reporting employee with ID " + reportsToId + " not found");
                }
            }
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        UUID employeeId = employee.getId();
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employeeId);
        if (!existingEmployeeOptional.isPresent()) {
            throw new RuntimeException("Employee with ID " + employeeId + " not found");
        }

        return employeeRepository.save(employee);
    }
    
}
