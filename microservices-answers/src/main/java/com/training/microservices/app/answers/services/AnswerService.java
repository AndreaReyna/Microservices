package com.training.microservices.app.answers.services;

import com.training.microservices.app.answers.models.entity.Answer;

public interface AnswerService {
	
	public Iterable<Answer> saveAll(Iterable<Answer> answers);
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId);
	public Iterable<Long> findTestsIdsWithAnswersByStudent(Long id);
	public Iterable<Answer> findByStudentId(Long studentId);
}
