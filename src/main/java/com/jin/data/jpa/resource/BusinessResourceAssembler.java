package com.jin.data.jpa.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Controller;

import com.jin.data.jpa.domain.BusinessEntity;

public class BusinessResourceAssembler<E extends BusinessEntity<?> ,R extends BusinessResource<E>, C extends Controller> extends ResourceAssemblerSupport<E,R>{

	private static final Logger logger = LoggerFactory.getLogger(BusinessResourceAssembler.class);
	private Class<R> resourceClass;
	public BusinessResourceAssembler(Class<C> controllerClass, Class<R> resourceClass ) {
		super(controllerClass , resourceClass);
		this.resourceClass =  resourceClass;
	}


	@Override
	public R toResource(E entity) {
		
		R r = null;
		try {
			r = resourceClass.newInstance();
			r.setEntity(entity);
			
		} catch (InstantiationException e) {
			logger.error("Cannot Instantiat the resource class. The error is: " + e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error("Cannot Instantiat the resource class. The error is: " + e.getMessage());
		}
		return r;
	}

}
