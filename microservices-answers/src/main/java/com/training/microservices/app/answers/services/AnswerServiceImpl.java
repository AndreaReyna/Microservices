package com.training.microservices.app.answers.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.microservices.app.answers.clients.TestFeignClient;
import com.training.microservices.app.answers.models.entity.Answer;
import com.training.microservices.app.answers.models.repository.AnswerRepository;
import com.training.microservices.commons.tests.models.entity.Question;
import com.training.microservices.commons.tests.models.entity.Test;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private TestFeignClient client;

	@Override
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		return answerRepository.saveAll(answers);
	}

	@Override
	public Iterable<Answer> findAnswerByStudentByTest(Long studentId, Long testId) {
		Test test = client.getTestById(testId);
		List<Question> questions = test.getQuestions();
		List<Long> questionIds = questions.stream().map(q-> q.getId()).collect(Collectors.toList());
		List<Answer> answers = (List<Answer>) answerRepository.findAnswerByStudentByQuestionIds(studentId, questionIds);
		answers = answers.stream().map(a->{
			questions.forEach(q->{
				if(q.getId() == a.getQuestionId()) {
					a.setQuestion(q);
				}
			});
			return a;
		}).collect(Collectors.toList());
		return answers;
	}

	@Override
	public Iterable<Long> findTestsIdsWithAnswersByStudent(Long studentId) {
		List<Answer> answersStudent = (List<Answer>) answerRepository.findByStudentId(studentId);
		List<Long> testIds = Collections.emptyList();
		if(answersStudent.size() > 0) {
			List<Long> questionIds = answersStudent.stream().map(a -> a.getQuestionId()).collect(Collectors.toList());
			testIds = client.getTestsIdsByQuestionsIdAnswers(questionIds);
		}
		return testIds;
	}

	@Override
	public Iterable<Answer> findByStudentId(Long studentId) {
		return answerRepository.findByStudentId(studentId);
	}

}
