package com.training.microservices.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.microservices.commons.students.models.entity.Student;

@FeignClient(name = "microservice-users")
public interface StudentFeignClient {

	@GetMapping("/students-for-course")
	public Iterable<Student> findStudentForCourse(@RequestParam Iterable<Long> ids);
}
