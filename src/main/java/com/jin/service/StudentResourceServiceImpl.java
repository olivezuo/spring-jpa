package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.domain.Student;
import com.jin.resource.StudentResource;

@Service
public class StudentResourceServiceImpl implements StudentResourceService {

	@Autowired
	StudentService studentService;
	
	@Override
	public StudentResource findStudent(Long id) {
		Student student = studentService.findStudent(id);
		StudentResource studentResource = new StudentResource(student);
		return studentResource;			
	}
	
	@Override
	public StudentResource addStudent(Student student) {
		Student newStudent = studentService.addStudent(student);
		StudentResource studentResource = new StudentResource(newStudent);
		return studentResource;			
		
	}

	
}
