import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent implements OnInit{

  title = "New Students";
  student:Student = new Student();
  error:any;

  constructor(private service:StudentService, private router:Router){}

  ngOnInit(){
  }

  public save():void{
    this.service.save(this.student).subscribe(student => {
      console.log(student);
      alert(`Student ${student.name} created successfully`);
      this.router.navigate(['/students']);
  }, err => {
    if(err.status === 400){
      this.error = err.error;
      console.log(this.error);
    }
  });
  }
}
