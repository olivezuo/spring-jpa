package com.jin.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jin.domain.Student;
import com.jin.resource.StudentResource;
import com.jin.service.StudentResourceService;
import com.jin.service.StudentService;

@RestController
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentResourceService studentResourceService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method=RequestMethod.POST, value="/students")
	public ResponseEntity<?> add(@Validated @RequestBody Student student) {
		logger.info("Receive a new Student: " + student.toString());
		StudentResource<Student> studentResource = studentResourceService.addStudent(student);
		return ResponseEntity.created(URI.create(studentResource.getLink("self").getHref())).build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/students/{id}")
	public StudentResource<Student> find(@PathVariable Long id) {
		return studentResourceService.findStudent(id);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public PagedResources<StudentResource<Student>> findAll(@PageableDefault(page=0,size=15) Pageable pageable) {
		if (pageable.getPageSize() >= 1000) {
			pageable = new PageRequest(pageable.getPageNumber(), 25);
		}
		return studentResourceService.findAllStudent(pageable);
	}

	
	@RequestMapping(method=RequestMethod.GET, value="/allstudents", produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Student> findAllNolink(@PageableDefault(page=0,size=15) Pageable pageable) {
		if (pageable.getPageSize() >= 1000) {
			pageable = new PageRequest(pageable.getPageNumber(), 25);
		}
		return studentService.findAllStudent(pageable);
	}

}
