package com.training.microservices.courses.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.commons.controllers.CommonController;
import com.training.microservices.courses.models.entity.Course;
import com.training.microservices.courses.services.CourseService;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> modify(@RequestBody Course course, @PathVariable Long id){
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		course.setCreateAt(c.get().getCreateAt());
		course.setId(c.get().getId());
		return ResponseEntity.ok(service.save(course));	
	}
}
