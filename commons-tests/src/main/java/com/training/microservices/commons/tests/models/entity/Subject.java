package com.training.microservices.commons.tests.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"children"}, allowSetters = true)
	private Subject parent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="parent", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
	private List<Subject> children;
	
	public Subject() {
		this.children = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject getParent() {
		return parent;
	}
	public void setParent(Subject parent) {
		this.parent = parent;
	}
	public List<Subject> getChildren() {
		return children;
	}
	public void setChildren(List<Subject> children) {
		this.children = children;
	}
}
