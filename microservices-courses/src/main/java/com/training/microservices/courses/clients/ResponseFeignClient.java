package com.training.microservices.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-answers")
public interface ResponseFeignClient {

	@GetMapping("/student/{studentId}/tests-answered")
	public Iterable<Long> getTestsIdsWithAnswersByStudent(@PathVariable Long studentId);
}
