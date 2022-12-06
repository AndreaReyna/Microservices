import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { CommonFormComponent } from '../common-form.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent extends CommonFormComponent<Student, StudentService> implements OnInit{

  private photo:File;

  constructor(service:StudentService, router:Router, route:ActivatedRoute){
    super(service, router, route);
    this.model = new Student;
    this.redirect = '/students';
    this.nameModel = Student.name;
  }
  
  public selectPhoto(event):void{
      this.photo = event.target.files[0];
      console.info(this.photo);
      if(this.photo.type.indexOf('image')<0){
        this.photo = null;
        Swal.fire(
          'Error', 
          'The file must be of type image',
          'error');
      }
  }

  public override save():void{
    if (!this.photo){
      super.save();
    }else{
      this.service.saveWithPhoto(this.model, this.photo).subscribe(student => {
        console.log(student);
        Swal.fire(
          'Success!',
          `Student ${student.name} successfully created`,
          'success'
        )
        this.router.navigate([this.redirect]);
    }, err => {
      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);
      }
    });
    }
  }

  public override modify():void{
    if (!this.photo){
      super.modify();
    }else{
      this.service.modifyWithPhoto(this.model, this.photo).subscribe(student => {
        console.log(student);
        Swal.fire(
          'Success!',
          `Student ${student.name} successfully created`,
          'success'
        )
        this.router.navigate([this.redirect]);
    }, err => {
      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);
      }
    });
    }
  }
}
