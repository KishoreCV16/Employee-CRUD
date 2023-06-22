package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository srepo;
	

	@Override
	public List<Student> getstudents() {
		return srepo.findAll();
	}
	
	@Override
	public Student saveStudent(Student student) {
		return srepo.save(student);
	}

	@Override
	public Student getonestudent(Long id) {
		Optional<Student> student = srepo.findById(id);
		if(student.isPresent()) {
			return student.get();
		}
		throw new RuntimeException("Student not found with id "+id);
	}

	@Override
	public void deletestudent(Long id) {
		srepo.deleteById(id);
	}

	@Override
	public Student updatestudent(Student student) {
		return srepo.save(student);
	}
	
}
