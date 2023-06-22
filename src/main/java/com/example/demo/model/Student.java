package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students_tbl")
public class Student implements Serializable {

	// private static final long serialVersionUID = 1033985090002344051L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="sno")
	private Long sno;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "email")
	private String email;

	@Column(name = "location")
	private String location;

	@Column(name = "department")
	private String department;

	/*
	 * public Student(String name, Long age, String email, String location, String
	 * department) { super(); Name = name; Age = age; Email = email; Location =
	 * location; Department = department; }
	 * 
	 * public String getName() { return Name; }
	 * 
	 * public void setName(String name) { Name = name; }
	 * 
	 * public Long getAge() { return Age; }
	 * 
	 * public void setAge(Long age) { Age = age; }
	 * 
	 * public String getEmail() { return Email; }
	 * 
	 * public void setEmail(String email) { Email = email; }
	 * 
	 * public String getLocation() { return Location; }
	 * 
	 * public void setLocation(String location) { Location = location; }
	 * 
	 * public String getDepartment() { return Department; }
	 * 
	 * public void setDepartment(String department) { Department = department; }
	 */
}
