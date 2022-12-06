import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_ENDPOINT } from '../config/app';
import { Course } from '../models/course';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends CommonService<Course>{

  protected override baseEndpoint = BASE_ENDPOINT + '/courses';

  constructor(http:HttpClient) {
    super(http);
   }
}
