package com.jin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jin.domain.Discipline;

@Repository
public interface DisciplineRepository extends PagingAndSortingRepository<Discipline, Long> {

}
