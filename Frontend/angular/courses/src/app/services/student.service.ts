import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/student';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends CommonService<Student>{

  protected override baseEndpoint = BASE_ENDPOINT + '/students';

  constructor(http:HttpClient) {
    super(http);
   }

   public saveWithPhoto(student:Student, file:File): Observable<Student>{
    const formData = new FormData();
    formData.append('file', file);
    formData.append('name', student.name);
    formData.append('lastName', student.lastName);
    formData.append('email', student.email);
    return this.http.post<Student>(this.baseEndpoint + '/create-with-photo', formData)
   }

   public modifyWithPhoto(student:Student, file:File): Observable<Student>{
    const formData = new FormData();
    formData.append('file', file);
    formData.append('name', student.name);
    formData.append('lastName', student.lastName);
    formData.append('email', student.email);
    return this.http.put<Student>(`${this.baseEndpoint}/modify-with-photo/${student.id}`, formData)
   }
}
