package com.training.microservices.app.users.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("/create-with-photo")
	public ResponseEntity<?> saveWithPhoto(@Validated Student student, BindingResult result, @RequestParam MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			student.setPhoto(file.getBytes());
		}
		return super.save(student, result);
	}
	
	@PutMapping("/modify-with-photo/{id}")
	public ResponseEntity<?> modifyWithPhoto(@Validated @PathVariable Long id, Student studentBody, BindingResult result, @RequestParam MultipartFile file) throws IOException{
		if	(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<Student> student = service.findById(id);
		if(student.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		studentBody.setId(student.get().getId());
		studentBody.setCreateAt(student.get().getCreateAt());
		if(!file.isEmpty()) {
			studentBody.setPhoto(file.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentBody));	
	}
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> showPhoto(@PathVariable Long id){
		Optional<Student> student = service.findById(id);
		if(student.isEmpty() || student.get().getPhoto() == null) {
			return ResponseEntity.notFound().build();	
		}
		Resource image = new ByteArrayResource(student.get().getPhoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
	@GetMapping("/students-for-course")
	public ResponseEntity<?> findStudentForCourse(@RequestParam List<Long> ids) {
		return ResponseEntity.ok(service.findAllById(ids));
	}
}
