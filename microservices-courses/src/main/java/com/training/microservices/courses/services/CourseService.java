package com.training.microservices.courses.services;

import com.training.microservices.commons.services.CommonService;
import com.training.microservices.commons.students.models.entity.Student;
import com.training.microservices.courses.models.entity.Course;

public interface CourseService extends CommonService<Course> {
	public Course findCourseByStudentId(Long id);
	public Iterable<Long> getTestsIdsWithAnswersByStudent(Long studentId);
	public Iterable<Student> findStudentForCourse(Iterable<Long> ids);
	public void deleteCourseStudentById(Long id);
}
