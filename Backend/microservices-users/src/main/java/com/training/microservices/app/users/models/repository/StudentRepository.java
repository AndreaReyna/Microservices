package com.training.microservices.app.users.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.training.microservices.commons.students.models.entity.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE UPPER(s.name) like upper(concat('%', ?1, '%')) or UPPER(s.lastName) like upper(concat('%', ?1, '%'))")
	public List<Student> findByNameOrLastName(String term);
	
	public Iterable<Student> findAllByOrderByIdAsc();
	
	public Page<Student> findAllByOrderByIdAsc(Pageable pageable);
}
