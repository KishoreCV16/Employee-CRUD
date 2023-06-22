package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService sservice;

	/*@Value("${app.name}")
	private String appname;

	@Value("${app.version}")
	private String appversion;

	@GetMapping("/version")
	public String Appinfo() {
		return appname + " - " + appversion;
	}*/

	@GetMapping("/students")
	public List<Student> getstudents() {
		return sservice.getstudents();
	}
	
	@GetMapping("/students/{roll}")
	public Student getstudent(@PathVariable Long roll) {
		return sservice.getonestudent(roll);
	}

	@PostMapping("/students")
	public Student saveStudent(@RequestBody Student student) {
		return sservice.saveStudent(student);
	}

	@PutMapping("/students/{roll}")
	public Student updateStudent(@PathVariable Long roll, @RequestBody Student student) {
		student.setId(roll);
		return sservice.updatestudent(student);
	}

	@DeleteMapping("/students")
	public void deletestudent(@RequestParam Long roll) {
		sservice.deletestudent(roll);
	}
}
