import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course.service';
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-courses-form',
  templateUrl: './courses-form.component.html',
  styleUrls: ['./courses-form.component.css']
})
export class CoursesFormComponent extends CommonFormComponent<Course, CourseService>{

  constructor(service:CourseService, router:Router, route:ActivatedRoute){
    super(service, router, route);
    this.model = new Course;
    this.redirect = '/courses';
    this.nameModel = Course.name;
  }
}
