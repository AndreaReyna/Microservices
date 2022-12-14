package com.training.microservices.app.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.microservices.app.users.client.CourseFeignClient;
import com.training.microservices.app.users.models.repository.StudentRepository;
import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.commons.students.models.entity.Student;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService {

	@Autowired
	private CourseFeignClient clientCourse;
	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastName(String term) {
		return repository.findByNameOrLastName(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Student> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public void deleteCourseStudentById(Long id) {
		clientCourse.deleteCourseStudentById(id);
	}

	@Override
	@Transactional()
	public void deleteById(Long id) {
		super.deleteById(id);
		this.deleteCourseStudentById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Student> findAll() {
		return repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> findAll(Pageable peageable) {
		return repository.findAllByOrderByIdAsc(peageable);
	}
	
}
