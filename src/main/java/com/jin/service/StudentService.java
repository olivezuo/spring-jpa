package com.jin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jin.domain.Student;

public interface StudentService {

	public Student addStudent(Student student);

	public Student findStudent(Long id);

	public Page<Student> findAllStudent(Pageable pageable);
}
