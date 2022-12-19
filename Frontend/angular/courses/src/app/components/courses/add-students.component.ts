import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course';
import { Student } from 'src/app/models/student';
import { CourseService } from 'src/app/services/course.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-students',
  templateUrl: './add-students.component.html',
  styleUrls: ['./add-students.component.css']
})
export class AddStudentsComponent implements OnInit {

  course: Course;
  studentsAdd: Student[] = [];
  students: Student[] = [];

  dataSource: MatTableDataSource<Student>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  pageSizeOptions: number[] = [3, 5, 10, 20, 50];

  tabIndex = 0;

  showColumns: string[] = ['name', 'lastName', 'selection'];
  showColumnsStudents: string[] = ['id', 'name', 'lastName', 'email', 'delete'];

  selection: SelectionModel<Student> = new SelectionModel<Student>(true, []);

  constructor(private route:ActivatedRoute, private courseService:CourseService, private studentService:StudentService){}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      this.courseService.getById(id).subscribe(c => {
        this.course = c;
        this.students = this.course.students;
        this.startPaginator();
      });
    });
  }

  private startPaginator(): void{
    this.dataSource = new MatTableDataSource<Student>(this.students);
    this.dataSource.paginator = this.paginator;
  }

  filter(name: string):void {
    name = name !== undefined? name.trim(): '';
    if(name !== ''){
      this.studentService.filterByName(name)
      .subscribe(students => this.studentsAdd = students.filter(s => {
        let filter = true;
        this.students.forEach(cs => {
          if(s.id === cs.id){
            filter = false;
          }
        });
        return filter;
      }));
    }
  }

  isAllSelected(): boolean {
    const numSelected = this.selection.selected.length;
    const numStudents = this.studentsAdd.length;
    return (numSelected === numStudents);
  }

  selectUnselectAll(): void {
    this.isAllSelected()?
    this.selection.clear(): 
    this.studentsAdd.forEach(s => this.selection.select(s));
  }

  add(): void {
    console.log(this.selection.selected);
    this.courseService.addStudent(this.course, this.selection.selected).subscribe(c => {
      this.tabIndex = 2;
      Swal.fire(
        'Assigned:',
        `Students successfully assigned to the course ${this.course.name}`,
        'success'
      );
      this.students = this.students.concat(this.selection.selected);
      this.startPaginator();
      this.studentsAdd = [];
      this.selection.clear();
    },
    e => {     
      if(e.status === 500){
        const mensaje = e.error.message as string;
        if(mensaje.indexOf('ConstraintViolationException') > -1){
          Swal.fire(
            'Warning:',
            'Cannot assign student already associated to another course',
            'error'
          );
        }
      }
    });
  }

  deleteStudent(student: Student): void {
    Swal.fire({
      title: 'Warning:',
      text: `¿Surely you want to remove ${student.name} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.courseService.deleteStudent(this.course, student)
        .subscribe(course => {
          this.students = this.students.filter(s => s.id !== student.id);
          this.startPaginator();
          Swal.fire(
            'Deleted:',
            `Student ${student.name} successfully removed from course ${course.name}.`,
            'success'
          );
        });    
      }
    });
  }
}
