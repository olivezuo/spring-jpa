package com.jin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jin.domain.Discipline;

@Repository
public interface DisciplineRepository extends PagingAndSortingRepository<Discipline, Long> {

	@Query("select new Discipline(d.id, d.name) from Discipline d")
	public Page<Discipline> findDisciplineForListing(Pageable pageable);

}
