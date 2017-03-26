package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import com.jin.controller.StudentController;
import com.jin.data.jpa.resource.BusinessResourceAssembler;
import com.jin.data.jpa.service.BusinessResourceService;
import com.jin.domain.Student;
import com.jin.resource.StudentResource;

@Service
public class StudentResourceServiceImpl extends BusinessResourceService implements StudentResourceService {

	@Autowired
	StudentService studentService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void init(){
		this.businessResourceAssembler = new BusinessResourceAssembler(StudentController.class, StudentResource.class);
	}

	@Override
	public StudentResource<Student> findStudent(Long id) {
		Student student = studentService.findStudent(id);
		StudentResource<Student> studentResource = new StudentResource<Student>(student);
		return studentResource;			
	}

	@Override
	public StudentResource<Student> addStudent(Student student) {
		Student newStudent = studentService.addStudent(student);
		StudentResource<Student> studentResource = new StudentResource<Student>(newStudent);
		return studentResource;			
		
	}

	@Override
	public PagedResources<StudentResource<Student>> findAllStudent(Pageable pageable) {
		Page<Student> students = studentService.findAllStudent(pageable);
		
		PagedResourcesAssembler<Student> assembler = new PagedResourcesAssembler<Student>(resolver, null); 
		
		@SuppressWarnings("unchecked")
		PagedResources<StudentResource<Student>> studentResources = assembler.toResource(students, businessResourceAssembler);		
		
		return studentResources;
	}

}
