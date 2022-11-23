package com.training.microservices.app.answers.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.training.microservices.app.answers.models.entity.Answer;

public interface AnswerRepository extends MongoRepository<Answer, String> {
	
	@Query("{'studentId': ?0, 'questionId':{ $in: ?1}}")
	public Iterable<Answer> findAnswerByStudentByQuestionIds(Long studentId, Iterable<Long> questionIds);
	
	@Query("{'studentId' : ?0}")
	public Iterable<Answer> findByStudentId(Long studentId);

	@Query("{'studentId' : ?0, 'question.test.id': ?1}")
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId);
	
	@Query(value = "{'studentId': ?0}", fields = "{'question.test.id': 1}")
	public Iterable<Answer> findTestsIdsWithAnswersByStudent(Long studentId);
}