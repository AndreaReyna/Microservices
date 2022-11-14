package com.training.microservices.app.tests.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.microservices.app.tests.models.repository.SubjectRepository;
import com.training.microservices.app.tests.models.repository.TestRepository;
import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.commons.tests.models.entity.Subject;
import com.training.microservices.commons.tests.models.entity.Test;

@Service
public class TestServiceImpl extends CommonServiceImpl<Test, TestRepository> implements TestService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Test> findByName(String term) {
		return repository.findByName(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Subject> findAllSubjects() {
		return subjectRepository.findAll();
	}
}
