package com.jin.service;

import com.jin.domain.Student;
import com.jin.resource.StudentResource;

public interface StudentResourceService {

	public StudentResource findStudent(Long id);

	public StudentResource addStudent(Student student);

}
