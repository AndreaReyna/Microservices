package com.training.microservices.app.tests.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.training.microservices.commons.tests.models.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
