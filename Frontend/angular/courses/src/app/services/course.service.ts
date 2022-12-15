import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_ENDPOINT } from '../config/app';
import { Course } from '../models/course';
import { Student } from '../models/student';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course>{

  protected override baseEndpoint = BASE_ENDPOINT + '/courses';

  constructor(http:HttpClient) {
    super(http);
   }

   addStudent(course: Course, students: Student[]): Observable<Course>{
    return this.http.put<Course>(`${this.baseEndpoint}/${course.id}/add-students`,
    students,
     {headers: this.headers});
  }

  deleteStudent(course: Course, student: Student): Observable<Course> {
    return this.http.put<Course>(`${this.baseEndpoint}/${course.id}/remove-student`,
    student,
    {headers: this.headers});
  }
}
