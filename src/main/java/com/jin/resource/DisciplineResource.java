package com.jin.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin.controller.StudentController;
import com.jin.data.jpa.domain.BusinessEntity;
import com.jin.data.jpa.resource.BusinessResource;

@Relation(collectionRelation = "disciplines")
public class DisciplineResource<E extends BusinessEntity<?>> extends BusinessResource<E> {

	public DisciplineResource(){
		
	}
	
	public DisciplineResource(E discipline){
		super(discipline);
		build();
		
	}
	
	@Override
	@JsonProperty("discipline")
	public E getEntity() {
		return this.entity;
	}


	@Override
	protected void build() {
		this.add(linkTo(StudentController.class).slash("disciplines").slash(this.entity.getId()).withSelfRel());
	}

	

}
