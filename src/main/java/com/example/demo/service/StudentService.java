package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentService {
	
	List<Student> getstudents();
	
	Student saveStudent(Student student);
	
	Student getonestudent(Long id);
	
	void deletestudent(Long id);
	
	Student updatestudent(Student student);
}
