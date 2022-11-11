package com.training.microservices.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.commons.services.CommonService;

@RestController
public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		return ResponseEntity.ok().body(service.findAll());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<E> student = service.findById(id);
		if(student.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.ok(student.get());	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody E entity){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));	
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();	
	}

}
