package com.training.microservices.courses.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.courses.models.entity.Course;
import com.training.microservices.courses.models.repository.CourseRepository;

@Service
public class CourseServiceImp extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

	@Override
	@Transactional(readOnly = true)
	public Course findCourseByStudentId(Long id) {
		return repository.findCourseByStudentId(id);
	}
}
