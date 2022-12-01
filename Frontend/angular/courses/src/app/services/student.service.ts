import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseEndpoint = 'http://localhost:8090/api/students';
  private headers:HttpHeaders = new HttpHeaders({'Content-Type':'application/json'});
  constructor(private http:HttpClient) { }

  public getAll():Observable<Student[]>{
    return this.http.get<Student[]>(this.baseEndpoint);
  }
  public getAllPaginated(page:string, size:string):Observable<any>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size);
    return this.http.get<any>(`${this.baseEndpoint}/paginated`, {params:params});
  }
  public getById(id:number):Observable<Student>{
    return this.http.get<Student>(this.baseEndpoint + '/' + id);
  }
  public save(student:Student):Observable<Student>{
    return this.http.post<Student>(this.baseEndpoint, student, {headers:this.headers});
  }
  public modify(student:Student):Observable<Student>{
    return this.http.put<Student>(this.baseEndpoint + '/' + student.id, student, {headers:this.headers});
  }
  public delete(id:number):Observable<void>{
    return this.http.delete<void>(this.baseEndpoint + '/' + id);
  }
}
