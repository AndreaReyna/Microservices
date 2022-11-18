package com.training.microservices.courses.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.commons.controllers.CommonController;
import com.training.microservices.commons.students.models.entity.Student;
import com.training.microservices.commons.tests.models.entity.Test;
import com.training.microservices.courses.models.entity.Course;
import com.training.microservices.courses.models.entity.CourseStudent;
import com.training.microservices.courses.services.CourseService;

@RestController
public class CourseController extends CommonController<Course, CourseService> {
	
	@Value("${config.balancer.test}")
	private String balancerTest;
	
	@GetMapping
	@Override
	public ResponseEntity<?> listAll(){
		List<Course> courses = ((List<Course>) service.findAll()).stream().map(c -> {
			c.getCourseStudents().forEach(cs ->{
				Student student = new Student();
				student.setId(cs.getStudentId());
				c.addStudent(student);
			});
			return c;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(courses);	
	}
	
	@GetMapping("/paginated")
	@Override
	public ResponseEntity<?> listAll(Pageable pageable){
		Page<Course> courses = service.findAll(pageable).map(course -> {
			course.getCourseStudents().forEach(cs ->{
				Student student = new Student();
				student.setId(cs.getStudentId());
				course.addStudent(student);
			});
			  
			return course;
	});
		return ResponseEntity.ok().body(courses);	
	}
	
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Course> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		Course course = o.get();
		if (course.getCourseStudents().isEmpty() == false) {
			List<Long> ids = course.getCourseStudents().stream().map(cs ->cs.getStudentId()).collect(Collectors.toList());
		List<Student> students = (List<Student>) service.findStudentForCourse(ids);
		course.setStudents(students);
		}
		return ResponseEntity.ok(course);	
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modify(@Validated @RequestBody Course course, BindingResult result, @PathVariable Long id){
		if	(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		course.setCreateAt(c.get().getCreateAt());
		course.setId(c.get().getId());
		return ResponseEntity.ok(service.save(course));	
	}
	
	@PutMapping("/{id}/add-students")
	public ResponseEntity<?> addStudents(@PathVariable Long id, @RequestBody List<Student> students){
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Course course = c.get();
		students.forEach(s ->{
			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setStudentId(s.getId());
			courseStudent.setCourse(course);
			course.addCourseStudent(courseStudent);
		});
		return ResponseEntity.ok(service.save(course));	
	}
	
	@PutMapping("/{id}/remove-student")
	public ResponseEntity<?> removeStudent(@PathVariable Long id, @RequestBody Student student){
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Course course = c.get();
		CourseStudent courseStudent = new CourseStudent();
		courseStudent.setStudentId(student.getId());
		course.removeCourseStudent(courseStudent);
		return ResponseEntity.ok(service.save(course));	
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> findCourseByStudentId(@PathVariable Long id){
		Course course = service.findCourseByStudentId(id);
		if (course != null) {
			List<Long> testsIds = (List<Long>) service.getTestsIdsWithAnswersByStudent(id);
			List<Test> tests = course.getTests().stream().map(test -> {
				if(testsIds.contains(test.getId())) {
					test.setAnswered(true);
				}
				return test;
			}).collect(Collectors.toList());
			course.setTests(tests);
		}
		return ResponseEntity.ok(course);	
	}
	
	@PutMapping("/{id}/add-tests")
	public ResponseEntity<?> addTests(@PathVariable Long id, @RequestBody List<Test> tests){
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Course course = c.get();
		tests.forEach(t ->{
			course.addTest(t);
		});
		return ResponseEntity.ok(service.save(course));	
	}
	
	@PutMapping("/{id}/remove-test")
	public ResponseEntity<?> removeTest(@PathVariable Long id, @RequestBody Test tests){
		Optional<Course> c = service.findById(id);
		if(c.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Course course = c.get();
		course.removeTest(tests);
		return ResponseEntity.ok(service.save(course));	
	}

	@GetMapping("/balancer-test")
	public ResponseEntity<?> balancerTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balancer", balancerTest);
		response.put("courses", service.findAll());
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete-student/{id}")
	public ResponseEntity<?> deleteCourseStudentById(@PathVariable Long id){
		service.deleteCourseStudentById(id);
		return ResponseEntity.noContent().build();
	}
}
