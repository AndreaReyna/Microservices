package com.training.microservices.app.tests.services;

import java.util.List;

import com.training.microservices.commons.services.CommonService;
import com.training.microservices.commons.tests.models.entity.Subject;
import com.training.microservices.commons.tests.models.entity.Test;

public interface TestService extends CommonService<Test> {
	public List<Test> findByName(String term);
	public Iterable<Subject> findAllSubjects();
	public Iterable<Long> findTestsIdsWithAnswersByQuestionIds(Iterable<Long> questionIds);
}
