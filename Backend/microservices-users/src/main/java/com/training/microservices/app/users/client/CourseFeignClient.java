package com.training.microservices.app.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-courses")
public interface CourseFeignClient {
	
	@DeleteMapping("/delete-student/{id}")
	public void deleteCourseStudentById(@PathVariable Long id);
}
