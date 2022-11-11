package com.training.microservices.courses.services;

import org.springframework.stereotype.Service;

import com.training.microservices.commons.services.CommonServiceImpl;
import com.training.microservices.courses.models.entity.Course;
import com.training.microservices.courses.models.repository.CourseRepository;

@Service
public class CourseServiceImp extends CommonServiceImpl<Course, CourseRepository> implements CourseService {
}
