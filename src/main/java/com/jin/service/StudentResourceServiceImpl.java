package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.jin.controller.StudentController;
import com.jin.domain.BusinessEntity;
import com.jin.domain.Student;
import com.jin.resource.BusinessResource;
import com.jin.resource.BusinessResourceAssembler;
import com.jin.resource.StudentResource;

@Service
public class StudentResourceServiceImpl<E extends BusinessEntity<?> ,R extends BusinessResource<E>, C extends Controller> implements StudentResourceService {

	@Autowired
	StudentService studentService;
	
	@Bean
	public BusinessResourceAssembler studentResourcesAssembler(){
		return new BusinessResourceAssembler(StudentController.class, StudentResource.class);
	}
	
	@Autowired
	HateoasPageableHandlerMethodArgumentResolver resolver;
	
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
		PagedResources<StudentResource<Student>> studentResources = assembler.toResource(students, studentResourcesAssembler());		
		
		return studentResources;
	}

}
