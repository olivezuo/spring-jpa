package com.jin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jin.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
