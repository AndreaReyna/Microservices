package com.training.microservices.app.users.services;

import java.util.List;

import com.training.microservices.commons.services.CommonService;
import com.training.microservices.commons.students.models.entity.Student;

public interface StudentService extends CommonService<Student>{
	public List<Student> findByNameOrLastName(String term);
}
