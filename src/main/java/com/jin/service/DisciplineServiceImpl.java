package com.jin.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jin.data.datasource.TargetDataSource;
import com.jin.domain.Discipline;
import com.jin.repository.DisciplineRepository;

@Service
public class DisciplineServiceImpl implements DisciplineService {

	private static final Logger logger = LoggerFactory.getLogger(DisciplineServiceImpl.class);
	@Autowired
	DisciplineRepository disciplineRepository;
	
	@Override
	@TargetDataSource(value="master")
	@Transactional(value=TxType.REQUIRES_NEW)
	public Discipline addDiscipline(Discipline discipline) {
		logger.info("Add new disciplines " + discipline.toString() );
		return disciplineRepository.save(discipline);		
	}
	
	@Override
	@TargetDataSource(value="master")
	public Discipline findDiscipline(Long id) {
		Discipline discipline = disciplineRepository.findOne(id);
		logger.info("We found a discipline " + discipline.toString());
		return discipline;	
	}

	@Override
	public Page<Discipline> findAllDiscipline(Pageable pageable) {
		
		return disciplineRepository.findDisciplineForListing(pageable);
	}

}
