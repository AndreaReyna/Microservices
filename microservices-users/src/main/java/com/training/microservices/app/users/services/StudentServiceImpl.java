package com.training.microservices.app.users.services;

import org.springframework.stereotype.Service;

import com.training.microservices.app.users.models.repository.StudentRepository;
import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.commons.students.models.entity.Student;

@Service
public class StudentServiceImpl extends CommonServiceImpl<Student, StudentRepository> implements StudentService {

}
