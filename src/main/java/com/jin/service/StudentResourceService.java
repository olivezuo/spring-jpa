package com.jin.service;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;

import com.jin.domain.Student;
import com.jin.resource.StudentResource;

public interface StudentResourceService {

	public StudentResource<Student> findStudent(Long id);

	public StudentResource<Student> addStudent(Student student);

	public PagedResources<StudentResource<Student>> findAllStudent(Pageable pageable);

}
