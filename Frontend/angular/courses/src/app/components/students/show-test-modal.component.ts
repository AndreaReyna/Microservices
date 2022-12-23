import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/models/answer';
import { Course } from 'src/app/models/course';
import { Test } from 'src/app/models/test';

@Component({
  selector: 'app-show-test-modal',
  templateUrl: './show-test-modal.component.html',
  styleUrls: ['./show-test-modal.component.css']
})

export class ShowTestModalComponent implements OnInit {

  course: Course;
  test: Test;
  answers: Answer[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  public modalRef: MatDialogRef<ShowTestModalComponent>) { }

  ngOnInit(): void {
    this.course = this.data.curso as Course;
    this.test = this.data.examen as Test;
    this.answers = this.data.respuestas as Answer[];
  }

  close(): void {
    this.modalRef.close();
  }

}