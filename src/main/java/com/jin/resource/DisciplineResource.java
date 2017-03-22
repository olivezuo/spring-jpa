package com.jin.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin.controller.StudentController;
import com.jin.domain.BusinessEntity;

public class DisciplineResource<E extends BusinessEntity<?>> extends BusinessResource<E> {

	public DisciplineResource(){
		
	}
	
	public DisciplineResource(E discipline){
		super(discipline);
		this.add(linkTo(StudentController.class).slash("disciplines").slash(discipline.getId()).withSelfRel());
	}
	
	@Override
	@JsonProperty("discipline")
	public E getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity(E discipline) {
		this.entity =  discipline;
		this.add(linkTo(StudentController.class).slash("disciplines").slash(discipline.getId()).withSelfRel());

	}


}
