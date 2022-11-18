package com.training.microservices.app.answers.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.training.microservices.app.answers.models.entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	@Query("SELECT a FROM Answer a JOIN FETCH a.question q JOIN fetch q.test t WHERE a.studentId =?1 and t.id=?2")
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId);
	
	@Query("SELECT t.id FROM Answer a JOIN a.question q JOIN q.test t WHERE a.studentId=?1 GROUP BY t.id")
	public Iterable<Long> findTestsIdsWithAnswersByStudent(Long id);
}
