import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_ENDPOINT } from '../config/app';
import { Subject } from '../models/subject';
import { Test } from '../models/test';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class TestService extends CommonService<Test>{

  protected override baseEndpoint = BASE_ENDPOINT + '/tests';
  
  constructor(http:HttpClient) {
    super(http);
   }

   public findAllSubject():Observable<Subject[]>{
    return this.http.get<Subject[]>(`${this.baseEndpoint}/subjects`);
   }

   public filterByName(name:string):Observable<Test[]>{
    return this.http.get<Test[]>(`${this.baseEndpoint}/filter/${name}`);
   }
}
