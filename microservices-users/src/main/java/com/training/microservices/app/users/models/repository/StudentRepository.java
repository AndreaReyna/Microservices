package com.training.microservices.app.users.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.microservices.commons.students.models.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.name like %?1% or s.lastName like %?1%")
	public List<Student> findByNameOrLastName(String term);
}
