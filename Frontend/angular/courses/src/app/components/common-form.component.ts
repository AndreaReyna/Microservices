import { Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';

@Injectable()
export abstract class CommonFormComponent<E extends Generic, S extends CommonService<E>> implements OnInit{
  
  model:E;
  error:any;
  protected redirect:string;
  protected nameModel:string;

  constructor(protected service:S, protected router:Router, protected route:ActivatedRoute){}

  ngOnInit(){
    this.route.paramMap.subscribe(params => {
      const id:number = +params.get('id');
      if(id){
        this.service.getById(id).subscribe(model => this.model = model)
      }
    })
  }

  public save():void{
    this.service.save(this.model).subscribe(model => {
      console.log(model);
      Swal.fire(
        'Success!',
        `${this.nameModel} ${model.name} successfully created`,
        'success'
      )
      this.router.navigate([this.redirect]);
  }, err => {
    if(err.status === 400){
      this.error = err.error;
      console.log(this.error);
    }
  });
  }

  public modify():void{
    this.service.save(this.model).subscribe(model => {
      console.log(model);
      Swal.fire(
        'Success!',
        `${this.nameModel} ${model.name} successfully modify`,
        'success'
      )
      this.router.navigate([this.redirect]);
  }, err => {
    if(err.status === 400){
      this.error = err.error;
      console.log(this.error);
    }
  });
  }
}
