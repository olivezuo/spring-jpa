package com.jin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jin.domain.Discipline;

public interface DisciplineService {

	public Discipline addDiscipline(Discipline student);

	public Discipline findDiscipline(Long id);

	public Page<Discipline> findAllDiscipline(Pageable pageable);

}
