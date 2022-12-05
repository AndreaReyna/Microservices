import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent extends CommonFormComponent<Student, StudentService> implements OnInit{

  constructor(service:StudentService, router:Router, route:ActivatedRoute){
    super(service, router, route);
    this.model = new Student;
    this.redirect = '/students';
    this.nameModel = Student.name;
  }
}
