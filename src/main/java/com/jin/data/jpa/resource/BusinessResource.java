package com.jin.data.jpa.resource;

import org.springframework.hateoas.ResourceSupport;

import com.jin.data.jpa.domain.BusinessEntity;

abstract public class BusinessResource<E extends BusinessEntity<?>> extends ResourceSupport {
	protected E entity;

	public BusinessResource(){
		
	}

	public BusinessResource(E entity) {
		this.entity = entity;
	}
	
	public void setEntity (E entity){
		this.entity = entity;
		build();
	}
	
	abstract public E getEntity ();
	
	abstract protected void build();

}
