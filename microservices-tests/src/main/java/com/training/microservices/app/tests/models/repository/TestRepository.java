package com.training.microservices.app.tests.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.microservices.commons.tests.models.entity.Test;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
	
	@Query("SELECT t FROM Test t WHERE t.name like %?1%")
	public List<Test> findByName(String term);

}
