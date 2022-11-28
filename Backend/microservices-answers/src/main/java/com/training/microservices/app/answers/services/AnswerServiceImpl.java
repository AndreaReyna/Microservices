package com.training.microservices.app.answers.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.microservices.app.answers.models.entity.Answer;
import com.training.microservices.app.answers.models.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return answerRepository.saveAll(answers);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId) {
		List<Answer> answers = (List<Answer>) answerRepository.findAnswerByStudentByTest(studentId, testId);
		return answers;
	}

	@Override
	public Iterable<Long> findTestsIdsWithAnswersByStudent(Long studentId) {
		List<Answer> answersStudent = (List<Answer>) answerRepository.findTestsIdsWithAnswersByStudent(studentId);
		List<Long> testIds = answersStudent.stream().filter(a -> a.getQuestion().getTest()!=null).map(a -> (a.getQuestion()).getTest().getId()).distinct().collect(Collectors.toList());
		return testIds;
	}

	@Override
	public Iterable<Answer> findByStudentId(Long studentId) {
		return answerRepository.findByStudentId(studentId);
	}

}
