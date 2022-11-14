package com.training.microservices.app.users.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.app.users.services.StudentService;
import com.training.microservices.commons.controllers.CommonController;
import com.training.microservices.commons.students.models.entity.Student;

@RestController
public class StudentController extends CommonController<Student, StudentService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modify(@Validated @PathVariable Long id, @RequestBody Student studentBody, BindingResult result){
		if	(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<Student> student = service.findById(id);
		if(student.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		studentBody.setId(student.get().getId());
		studentBody.setCreateAt(student.get().getCreateAt());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentBody));	
	}
	
	@GetMapping("/filter/{term}")
	public ResponseEntity<?> filter(@PathVariable String term){
		return ResponseEntity.ok(service.findByNameOrLastName(term));
	}
}
