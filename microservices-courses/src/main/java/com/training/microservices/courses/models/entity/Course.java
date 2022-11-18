package com.training.microservices.courses.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.training.microservices.commons.students.models.entity.Student;
import com.training.microservices.commons.tests.models.entity.Test;


@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(min = 3, max = 100)
	private String name;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"course"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CourseStudent> courseStudents;

	@Transient
	private List<Student> students;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Test> tests;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Course() {
		this.students = new ArrayList<>();
		this.tests = new ArrayList<>();
		this.courseStudents = new ArrayList<>();
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	public void addTest(Test test) {
		this.tests.add(test);
	}
	
	public void removeTest(Test test) {
		this.tests.remove(test);
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<CourseStudent> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(List<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}
	
	public void addCourseStudent(CourseStudent courseStudent) {
		this.courseStudents.add(courseStudent);
	}
	
	public void removeCourseStudent(CourseStudent courseStudent) {
		this.courseStudents.remove(courseStudent);
	}	
}
