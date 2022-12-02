import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent implements OnInit{
  
  student:Student = new Student();
  error:any;

  constructor(private service:StudentService, private router:Router, private route:ActivatedRoute){}

  ngOnInit(){
    this.route.paramMap.subscribe(params => {
      const id:number = +params.get('id');
      if(id){
        this.service.getById(id).subscribe(student => this.student = student)
      }
    })
  }

  public save():void{
    this.service.save(this.student).subscribe(student => {
      console.log(student);
      Swal.fire(
        'Success!',
        `Student ${student.name} successfully created`,
        'success'
      )
      this.router.navigate(['/students']);
  }, err => {
    if(err.status === 400){
      this.error = err.error;
      console.log(this.error);
    }
  });
  }

  public modify():void{
    this.service.save(this.student).subscribe(student => {
      console.log(student);
      Swal.fire(
        'Success!',
        `Student ${student.name} successfully modify`,
        'success'
      )
      this.router.navigate(['/students']);
  }, err => {
    if(err.status === 400){
      this.error = err.error;
      console.log(this.error);
    }
  });
  }
}
