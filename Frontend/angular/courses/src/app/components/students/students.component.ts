import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';

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

  public delete(student:Student):void{
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete student ${student.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.service.delete(student.id).subscribe(() => {
          this.students = this.students.filter(s => s!==student);
          Swal.fire(
            'Success!',
            `Student ${student.name} successfully deleted`,
            'success'
          )
        });
      }
    });
  }
}