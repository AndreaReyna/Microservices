import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  
  title:string = 'List of students';
  students:Student[];

  constructor(private service:StudentService){}

  ngOnInit(){
    this.service.getAll().subscribe(students => this.students = students);
  }
}
