package com.training.microservices.app.answers.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.microservices.commons.tests.models.entity.Test;

@FeignClient(name = "microservice-tests")
public interface TestFeignClient {

	@GetMapping("/{id}")
	public Test getTestById(@PathVariable Long id);
	
	@GetMapping("/answers-by-questions")
	public List<Long> getTestsIdsByQuestionsIdAnswers(@RequestParam List<Long> questionIds);
}
