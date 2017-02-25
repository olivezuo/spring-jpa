package com.jin.service;

import org.springframework.stereotype.Service;

import com.jin.domain.Student;

@Service
public interface StudentService {

	public Student addStudent(Student student);

	public Student findStudent(Long id);
}
