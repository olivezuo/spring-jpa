package com.jin.service;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;

import com.jin.domain.Discipline;
import com.jin.resource.DisciplineResource;

public interface DisciplineResourceService {

	public DisciplineResource<Discipline> findDiscipline(Long id);

	public DisciplineResource<Discipline> addDiscipline(Discipline student);

	public PagedResources<DisciplineResource<Discipline>> findAllDiscipline(Pageable pageable);

}
