package com.jin.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Controller;

import com.jin.domain.BusinessEntity;

public class BusinessResourceAssembler<E extends BusinessEntity<?> ,R extends BusinessResource<E>, C extends Controller> extends ResourceAssemblerSupport<E,R>{

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	
	@Override
	public List<R> toResources(Iterable<? extends E> entities) {
		List<R> entitiesResources = new ArrayList<R>();
		for(E entity : entities){
			entitiesResources.add(toResource(entity));
		}
		return entitiesResources;
		
	}

}
