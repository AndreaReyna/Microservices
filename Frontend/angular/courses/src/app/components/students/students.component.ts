import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { CommonListComponent } from '../common-list.component';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent extends CommonListComponent<Student, StudentService> implements OnInit {

  constructor(service:StudentService){
    super(service);
    this.title = 'List of students';
    this.nameModel = Student.name;
  }
}