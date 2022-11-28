package com.training.microservices.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.commons.students.models.entity.Student;
import com.training.microservices.courses.clients.ResponseFeignClient;
import com.training.microservices.courses.clients.StudentFeignClient;
import com.training.microservices.courses.models.entity.Course;
import com.training.microservices.courses.models.repository.CourseRepository;

@Service
public class CourseServiceImp extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

	@Autowired
	private ResponseFeignClient client;
	
	@Autowired
	private StudentFeignClient clientStudent;
	
	@Override
	@Transactional(readOnly = true)
	public Course findCourseByStudentId(Long id) {
		return repository.findCourseByStudentId(id);
	}

	@Override
	public Iterable<Long> getTestsIdsWithAnswersByStudent(Long studentId) {
		return client.getTestsIdsWithAnswersByStudent(studentId);
	}

	@Override
	public Iterable<Student> findStudentForCourse(Iterable<Long> ids) {
		return clientStudent.findStudentForCourse(ids);
	}

	@Override
	@Transactional()
	public void deleteCourseStudentById(Long id) {
		repository.deleteCourseStudentById(id);		
	}
}
