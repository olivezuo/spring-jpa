package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import com.jin.controller.DisciplineController;
import com.jin.data.jpa.resource.BusinessResourceAssembler;
import com.jin.data.jpa.service.BusinessResourceService;
import com.jin.domain.Discipline;
import com.jin.resource.DisciplineResource;

@Service
public class DisciplineResourceServiceImpl extends BusinessResourceService implements DisciplineResourceService {

	@Autowired
	DisciplineService disciplineService;
	
	@Bean
	public BusinessResourceAssembler disciplineResourcesAssembler(){
		return new BusinessResourceAssembler(DisciplineController.class, DisciplineResource.class);
	}
		
	@Override
	public DisciplineResource<Discipline> findDiscipline(Long id) {
		Discipline discipline = disciplineService.findDiscipline(id);
		DisciplineResource<Discipline> disciplineResource = new DisciplineResource<Discipline>(discipline);
		return disciplineResource;			
	}
	
	@Override
	public DisciplineResource<Discipline> addDiscipline(Discipline discipline) {
		Discipline newDiscipline = disciplineService.addDiscipline(discipline);
		DisciplineResource<Discipline> disciplineResource = new DisciplineResource<Discipline>(newDiscipline);
		return disciplineResource;			
		
	}

	@Override
	public PagedResources<DisciplineResource<Discipline>> findAllDiscipline(Pageable pageable) {
		Page<Discipline> disciplines = disciplineService.findAllDiscipline(pageable);
		
		PagedResourcesAssembler<Discipline> assembler = new PagedResourcesAssembler<Discipline>(resolver, null); 
		PagedResources<DisciplineResource<Discipline>> disciplineResources = assembler.toResource(disciplines, disciplineResourcesAssembler());
		
		return disciplineResources;
	}

}
