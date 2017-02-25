package com.jin.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jin.domain.Student;
import com.jin.resource.StudentResource;
import com.jin.service.StudentResourceService;

@RestController
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentResourceService studentResourceService;
	
	@RequestMapping(method=RequestMethod.POST, value="/students")
	public ResponseEntity<?> add(@RequestBody Student student) {
		StudentResource studentResource = studentResourceService.addStudent(student);
		return ResponseEntity.created(URI.create(studentResource.getLink("self").getHref())).build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/students/{id}")
	public StudentResource find(@PathVariable Long id) {
		return studentResourceService.findStudent(id);
	}

}
