import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/models/answer';
import { Course } from 'src/app/models/course';
import { Question } from 'src/app/models/question';
import { Student } from 'src/app/models/student';
import { Test } from 'src/app/models/test';

@Component({
  selector: 'app-answer-test-modal',
  templateUrl: './answer-test-modal.component.html',
  styleUrls: ['./answer-test-modal.component.css']
})
export class AnswerTestModalComponent implements OnInit {

  course:Course;
  test:Test; 
  student:Student; 
  

  answers:Map<number, Answer> = new Map<number, Answer>();

  constructor(@Inject(MAT_DIALOG_DATA) public data:any, public modalRef:MatDialogRef<AnswerTestModalComponent>){}

  ngOnInit(): void {
    this.course = this.data.course as Course;
    this.student = this.data.student as Student;
    this.test = this.data.test as Test;
  }

  cancel():void{
    this.modalRef.close();
  }

  answer(question: Question, event): void {
    const text = event.target.value as string;
    const answer  = new Answer();
    answer.student = this.student;
    answer.question = question;
  
    const test = new Test();
    test.id = this.test.id;
    test.name = this.test.name;

    answer.question.test = test;
    answer.text = text;

    this.answers.set(question.id, answer);
    console.log(this.answers);
  }
}
