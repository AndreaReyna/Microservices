import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Test } from '../models/test';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class TestService extends CommonService<Test>{

  protected override baseEndpoint = 'http://localhost:8090/api/tests';
  
  constructor(http:HttpClient) {
    super(http);
   }
}
