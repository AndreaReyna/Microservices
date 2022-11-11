package com.training.microservices.courses.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.training.microservices.courses.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
