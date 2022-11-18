package com.training.microservices.courses.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.microservices.courses.models.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
	@Query("SELECT c FROM Course c JOIN FETCH c.courseStudents s WHERE s.studentId=?1")
	public Course findCourseByStudentId(Long id);
	
	@Modifying
	@Query("DELETE from CourseStudent cs WHERE cs.studentId=?1")
	public void deleteCourseStudentById(Long id);
}
