package com.training.microservices.app.tests.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.app.tests.services.TestService;
import com.training.microservices.commons.controllers.CommonController;
import com.training.microservices.commons.tests.models.entity.Test;

@RestController
public class TestController extends CommonController<Test, TestService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> modify (@Validated @RequestBody Test test, BindingResult result, @PathVariable Long id){
		if	(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<Test> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Test testDb = o.get();
		testDb.setName(test.getName());
		
		testDb.getQuestions().stream()
		.filter(q -> !test.getQuestions().contains(q))
		.forEach(testDb::removeQuestion);
		
		testDb.setQuestions(test.getQuestions());
		
		return ResponseEntity.ok(service.save(testDb));
	}
	
	@GetMapping("/filter/{term}")
	public ResponseEntity<?> filter(@PathVariable String term){
		return ResponseEntity.ok(service.findByName(term));
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<?> getSubjects(){
		return ResponseEntity.ok(service.findAllSubjects());
	}
	
	@GetMapping("/answers-by-questions")
	public ResponseEntity<?> getTestsIdsByQuestionsIdAnswers(@RequestParam List<Long> questionIds){
		return ResponseEntity.ok(service.findTestsIdsWithAnswersByQuestionIds(questionIds));
	}
	
}
