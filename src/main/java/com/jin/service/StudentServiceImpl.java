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
import com.jin.domain.Student;
import com.jin.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	@TargetDataSource(value="master")
	@Transactional(value=TxType.REQUIRES_NEW)
	public Student addStudent(Student student) {
		logger.info("Add new students " + student.toString() );
		return studentRepository.save(student);		
	}
	
	@Override
	@TargetDataSource(value="master")
	public Student findStudent(Long id) {
		Student student = studentRepository.findOne(id);
		logger.info("We found a student " + student.toString());
		return student;	
	}

	@Override
	public Page<Student> findAllStudent(Pageable pageable) {
		
		return studentRepository.findAll(pageable);
	}

}
