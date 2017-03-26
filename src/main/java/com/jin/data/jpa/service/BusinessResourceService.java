package com.jin.data.jpa.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

import com.jin.data.jpa.resource.BusinessResourceAssembler;

abstract public class BusinessResourceService {

	
	@Autowired
	protected HateoasPageableHandlerMethodArgumentResolver resolver;
	
	@SuppressWarnings("rawtypes")
	protected BusinessResourceAssembler businessResourceAssembler;

	@PostConstruct
	abstract public void init();

}
