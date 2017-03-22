package com.jin.data.jpa.resource;

import org.springframework.hateoas.ResourceSupport;

import com.jin.data.jpa.domain.BusinessEntity;

abstract public class BusinessResource<E extends BusinessEntity<?>> extends ResourceSupport {
	protected E entity;
	public BusinessResource(E entity) {
		this.entity = entity;
	}
	
	abstract public void setEntity (E entity);
	
	abstract public E getEntity ();

	public BusinessResource(){
		
	}
}
