import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { Course } from 'src/app/models/course';
import { Student } from 'src/app/models/student';
import { Test } from 'src/app/models/test';
import { AnswerService } from 'src/app/services/answer.service';
import { CourseService } from 'src/app/services/course.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
import { AnswerTestModalComponent } from './answer-test-modal.component';

@Component({
  selector: 'app-answer-test',
  templateUrl: './answer-test.component.html',
  styleUrls: ['./answer-test.component.css']
})
export class AnswerTestComponent implements OnInit {

  student:Student;
  course:Course;
  tests:Test[] = [];

  showColumnsTest = ['id', 'name', 'subject', 'questions','answer', 'show']

  dataSource: MatTableDataSource<Test>;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  pageSizeOptions: number[] = [3, 5, 10, 20, 50];

  constructor(private route:ActivatedRoute, private studentService:StudentService, private courseService:CourseService, private answerService:AnswerService, public dialog: MatDialog){};

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      this.studentService.getById(id).subscribe(student =>{
        this.student = student;
        this.courseService.getCourseByStudentId(this.student).subscribe(
          course => {
            this.course = course;
            this.tests = (course && course.tests) ? course.tests : [];
            this.dataSource = new MatTableDataSource<Test>(this.tests);
            this.dataSource.paginator = this.paginator;
          }
        )
      });
    });  
  }

  answerTest(test:Test):void{
    const modalRef = this.dialog.open(AnswerTestModalComponent, {
      width: '750px',
      data: {course:this.course, student:this.student, test:test}
    });

    modalRef.afterClosed().subscribe((answerMap: Map<number, Answer>) => {
      console.log(answerMap);
      if(answerMap){
        const answers: Answer[] = Array.from(answerMap.values());
        this.answerService.save(answers).subscribe(a =>{
          test.answered = true;
          Swal.fire(
            'Sent:',
            'Questions submitted successfully',
            'success'
          );
          console.log(a);
        });
      }
    });
  }
}
