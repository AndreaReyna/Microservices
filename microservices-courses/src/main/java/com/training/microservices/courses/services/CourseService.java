package com.training.microservices.courses.services;

import com.training.microservices.commons.services.CommonService;
import com.training.microservices.courses.models.entity.Course;

public interface CourseService extends CommonService<Course> {
	public Course findCourseByStudentId(Long id);
	public Iterable<Long> getTestsIdsWithAnswersByStudent(Long studentId);
}
