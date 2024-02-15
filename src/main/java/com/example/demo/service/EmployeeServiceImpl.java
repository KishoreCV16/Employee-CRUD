package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmailService emailService;

    @Override
    public Page<Employee> getAllEmployees(int pageNumber, int pageSize, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return employeeRepository.findAll(pageable);
    }
    
    @Override
    public UUID saveEmployee(Employee employee) {
    	if (employeeRepository.count() == 0) {
            employee.setReportsTo(null);
        } else {
            UUID reportsToId = employee.getReportsTo();
            if (reportsToId != null) {
                Optional<Employee> reportsToEmployeeOptional = employeeRepository.findById(reportsToId);
                if (!reportsToEmployeeOptional.isPresent()) {
                    throw new RuntimeException("Reporting employee with ID " + reportsToId + " not found");
                }
            }
        }
        Employee savedEmployee = employeeRepository.save(employee);
        sendEmailToLevel1Manager(savedEmployee);
        return savedEmployee.getId();
    }
    
    private void sendEmailToLevel1Manager(Employee employee) {
        Employee manager = findLevel1Manager(employee);
        String subject = "New Employee Addition: " + employee.getEmployeeName();
        String message = employee.getEmployeeName() + " will now work under you. Mobile number is " +
                         employee.getPhoneNumber() + " and email is " + employee.getEmail();
        emailService.sendEmail(manager.getEmail(), subject, message);
    }

    private Employee findLevel1Manager(Employee employee) {
        if (employee == null || employee.getReportsTo() == null) {
            return null;
        }
        UUID managerId = employee.getReportsTo();
        Optional<Employee> managerOptional = employeeRepository.findById(managerId);
        if (managerOptional.isPresent()) {
            return managerOptional.get();
        } else {
            return null;
        }
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
    
    public Employee getNthLevelManager(UUID employeeId, int level) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            UUID managerId = employee.getReportsTo();
            for (int i = 0; i < level; i++) {
                if (managerId == null) {
                    return null;
                }
                Optional<Employee> managerOptional = employeeRepository.findById(managerId);
                if (managerOptional.isPresent()) {
                    employee = managerOptional.get();
                    managerId = employee.getReportsTo();
                } else {
                    return null;
                }
            }
            return employee;
        } else {
            return null;
        }
    }
    
}
