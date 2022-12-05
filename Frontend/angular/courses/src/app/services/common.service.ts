import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Generic } from '../models/generic';

@Injectable({
  providedIn: 'root'
})
export abstract class CommonService<E extends Generic> {

  protected baseEndpoint: string;
  protected headers:HttpHeaders = new HttpHeaders({'Content-Type':'application/json'});
  constructor(protected http:HttpClient) { }

  public getAll():Observable<E[]>{
    return this.http.get<E[]>(this.baseEndpoint);
  }
  public getAllPaginated(page:string, size:string):Observable<any>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size);
    return this.http.get<any>(`${this.baseEndpoint}/paginated`, {params:params});
  }
  public getById(id:number):Observable<E>{
    return this.http.get<E>(`${this.baseEndpoint}/${id}`);
  }
  public save(e:E):Observable<E>{
    return this.http.post<E>(this.baseEndpoint, e, {headers:this.headers});
  }
  public modify(e:E):Observable<E>{
    return this.http.put<E>(this.baseEndpoint + '/' + e.id, e, {headers:this.headers});
  }
  public delete(id:number):Observable<void>{
    return this.http.delete<void>(this.baseEndpoint + '/' + id);
  }
}
