package com.training.microservices.app.answers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.microservices.app.answers.models.entity.Answer;
import com.training.microservices.app.answers.models.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;

	@Override
	@Transactional
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return answerRepository.saveAll(answers);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId) {
		return answerRepository.findAnswerByStudentByTest(studentId, testId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findTestsIdsWithAnswersByStudent(Long studentId) {
		return answerRepository.findTestsIdsWithAnswersByStudent(studentId);
	}

}
