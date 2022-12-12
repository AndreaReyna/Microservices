import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'src/app/models/subject';
import { Test } from 'src/app/models/test';
import { TestService } from 'src/app/services/test.service';
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-tests-form',
  templateUrl: './tests-form.component.html',
  styleUrls: ['./tests-form.component.css']
})
export class TestsFormComponent extends CommonFormComponent<Test, TestService>{

  subjectsParent:Subject[] = [];
  subjectsChild:Subject[] = [];


  constructor(service:TestService, router:Router, route:ActivatedRoute){
    super(service, router, route);
    this.model = new Test;
    this.redirect = '/tests';
    this.nameModel = Test.name;
  }

  override ngOnInit(){
    this.route.paramMap.subscribe(params => {
      const id:number = +params.get('id');
      if(id){
        this.service.getById(id).subscribe(model => {
          this.model = model;
          this.children();
        });
      }
    });

    this.service.findAllSubject().subscribe(subjects=>
      this.subjectsParent = subjects.filter(a => !a.parent));
  }

  children():void{
    this.subjectsChild = this.model.subjectParent ? this.model.subjectParent.children : [];
  }

  compareSubject(s1: Subject, s2:Subject):boolean{
    if (s1===undefined && s2===undefined){
      return true;
    }
    return (s1===null || s2===null || s1===undefined || s2===undefined)
      ? false : s1.id === s2.id;
  }
}
