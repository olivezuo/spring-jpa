package com.jin.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin.controller.DisciplineController;
import com.jin.controller.StudentController;
import com.jin.data.jpa.domain.BusinessEntity;
import com.jin.data.jpa.resource.BusinessResource;
import com.jin.domain.Discipline;
import com.jin.domain.Student;

@Relation(collectionRelation = "students")
public class StudentResource<E extends BusinessEntity<?>> extends BusinessResource<E> {

	public StudentResource(){
		
	}
	
	public StudentResource(E student) {
		super(student);
		build();
		
	}

	@Override
	@JsonProperty("student")
	public E getEntity() {
		return this.entity;
	}
	
	protected void build() {
		this.add(linkTo(StudentController.class).slash("students").slash(this.entity.getId()).withSelfRel());
		List<Discipline> disciplines = ((Student)this.entity).getDisciplines();
		if (null != disciplines) {
			for (Discipline discipline : disciplines) {
				this.add(linkTo(DisciplineController.class).slash("disciplines").slash(discipline.getId()).withRel("disciplines"));
			}
		}
		
	}
	
}
