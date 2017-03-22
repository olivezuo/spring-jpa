package com.jin.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

public class BusinessResourceService {

	@Autowired
	protected HateoasPageableHandlerMethodArgumentResolver resolver;

}
