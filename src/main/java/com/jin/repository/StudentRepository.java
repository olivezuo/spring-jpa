package com.jin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jin.domain.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

	public Page<Student> findAllByOrderByIdDesc(Pageable pageable);
}
