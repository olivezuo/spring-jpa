package com.jin.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ResourceSupport;

import com.jin.controller.StudentController;
import com.jin.domain.Student;

public class StudentOrginalResource extends ResourceSupport{

	private final Student student;
	
	public StudentOrginalResource(Student student) {
		this.student = student;
		this.add(linkTo(StudentController.class).slash("students").slash(student.getId()).withSelfRel());
	}
	
	public Student getStudent() {
		return this.student;
	}

}
