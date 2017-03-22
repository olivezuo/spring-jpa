package com.jin.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin.controller.DisciplineController;
import com.jin.controller.StudentController;
import com.jin.domain.BusinessEntity;
import com.jin.domain.Discipline;
import com.jin.domain.Student;

public class StudentResource<E extends BusinessEntity<?>> extends BusinessResource<E> {

	public StudentResource(){
		
	}
	
	public StudentResource(E student) {
		super(student);
		this.add(linkTo(StudentController.class).slash("students").slash(student.getId()).withSelfRel());
		List<Discipline> disciplines = ((Student)student).getDisciplines();
		if (null != disciplines) {
			for (Discipline discipline : disciplines) {
				this.add(linkTo(DisciplineController.class).slash("disciplines").slash(discipline.getId()).withRel("allDisciplines"));
			}
		}
	}

	@Override
	@JsonProperty("student")
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity(E student) {
		this.entity = student;
		this.add(linkTo(StudentController.class).slash("students").slash(student.getId()).withSelfRel());
		List<Discipline> disciplines = ((Student)student).getDisciplines();
		for (Discipline discipline : disciplines) {
			this.add(linkTo(DisciplineController.class).slash("disciplines").slash(discipline.getId()).withRel("allDisciplines"));
		}
	}
	
}
