import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course>{

  protected override baseEndpoint = 'http://localhost:8090/api/courses';

  constructor(http:HttpClient) {
    super(http);
   }
}
