package com.jin.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jin.domain.Discipline;
import com.jin.resource.DisciplineResource;
import com.jin.service.DisciplineResourceService;

@RestController
public class DisciplineController {
	private static final Logger logger = LoggerFactory.getLogger(DisciplineController.class);
	
	@Autowired
	DisciplineResourceService disciplineResourceService;
	
	@RequestMapping(method=RequestMethod.POST, value="/disciplines")
	public ResponseEntity<?> add(@Validated @RequestBody Discipline discipline) {
		logger.info("Receive a new Discipline: " + discipline.toString());
		DisciplineResource<Discipline> disciplineResource = disciplineResourceService.addDiscipline(discipline);
		return ResponseEntity.created(URI.create(disciplineResource.getLink("self").getHref())).build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/disciplines/{id}")
	public DisciplineResource<Discipline> find(@PathVariable Long id) {
		return disciplineResourceService.findDiscipline(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/disciplines", produces=MediaType.APPLICATION_JSON_VALUE)
	public PagedResources<DisciplineResource<Discipline>> findAll(@PageableDefault(page=1,size=15) Pageable pageable) {
		if (pageable.getPageSize() >= 1000) {
			pageable = new PageRequest(pageable.getPageNumber(), 25);
		}
		return disciplineResourceService.findAllDiscipline(pageable);
	}

}
