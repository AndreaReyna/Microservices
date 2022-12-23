import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_ENDPOINT } from '../config/app';
import { Answer } from '../models/answer';
import { Student } from '../models/student';
import { Test } from '../models/test';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  private baseEndpoint = BASE_ENDPOINT + '/answers';

  constructor(private http: HttpClient) { }

  save(answers: Answer[]): Observable<Answer[]>{
    return this.http.post<Answer[]>(this.baseEndpoint, answers, {headers: this.headers});
  }
  
  getAnswersByStudentByTest(student:Student, test:Test):Observable<Answer[]>{
    return this.http.get<Answer[]>(`${this.baseEndpoint}/student/${student.id}/test/${test.id}`);
  }
}
