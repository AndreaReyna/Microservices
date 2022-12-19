import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { map, mergeMap } from 'rxjs/operators';
import { Course } from 'src/app/models/course';
import { Test } from 'src/app/models/test';
import { CourseService } from 'src/app/services/course.service';
import { TestService } from 'src/app/services/test.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-tests',
  templateUrl: './add-tests.component.html',
  styleUrls: ['./add-tests.component.css']
})
export class AddTestsComponent implements OnInit{

  course:Course;
  autocompleteControl = new FormControl();
  testsFiltered:Test[] = [];
  testsAdd: Test[] = [];
  tests: Test[] = [];

  dataSource: MatTableDataSource<Test>;
  @ViewChild(MatPaginator, {static:true}) paginator:MatPaginator;
  pageSizeOptions = [3, 5, 10, 20, 50];

  showColumns = ['name', 'subject', 'delete'];
  showColumnsTest = ['id', 'name', 'subject', 'delete'];
  tabIndex = 0;

  constructor(private route:ActivatedRoute, private router:Router, private courseService:CourseService, private testService:TestService){}

  ngOnInit(){
      this.route.paramMap.subscribe(params =>{
        const id:number = + params.get('id');
        this.courseService.getById(id).subscribe(c=> {
          this.course = c,
          this.tests = this.course.tests;
          this.startPaginator();
        });
      });
      this.autocompleteControl.valueChanges.pipe(
        map(value => typeof value === 'string' ? value : value.name),
        mergeMap(value => value ? this.testService.filterByName(value):[])
      ).subscribe(tests => this.testsFiltered = tests);
    }

    private startPaginator(){
      this.dataSource = new MatTableDataSource<Test>(this.tests);
      this.dataSource.paginator = this.paginator;
    }

    showName(test? :Test):string{
      return test? test.name:'';
    }

    selectedTest(event:MatAutocompleteSelectedEvent):void{
      const test = event.option.value as Test;
      if (!this.exist(test.id)) {
        

      this.testsAdd = this.testsAdd.concat(test);
      
      console.log(this.testsAdd);
    }else{
      Swal.fire(
        'Warning:',
        'Cannot assign test already associated to course',
        'error'
      );
    }
    this.autocompleteControl.setValue('');
    event.option.deselect();
    event.option.focus();
    }

    private exist(id:number): boolean{
      let exist = false;
      this.testsAdd.concat(this.tests)
      .forEach(t=>{
        if (id === t.id) {
          exist = true;
        }
      });
      return exist;
    }

    deleteOfAdd(test: Test){
      this.testsAdd = this.testsAdd.filter(
        t=> test.id !== t.id
      );
    }

    add(): void {
      console.log(this.testsAdd)
      this.courseService.addTests(this.course, this.testsAdd)
      .subscribe(course => {
        this.tests = this.tests.concat(this.testsAdd);
        this.startPaginator();
        this.testsAdd = [];
        Swal.fire(
          'Assigned:',
          `Tests successfully assigned to the course ${this.course.name}`,
          'success'
        );
        this.tabIndex = 2;
      })
    }

    deleteTest(test:Test){
      Swal.fire({
        title: 'Warning:',
        text: `¿Surely you want to remove ${test.name} ?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'yes, delete it!'
      }).then((result) => {
        if (result.value) {
          this.courseService.deleteTests(this.course, test)
          .subscribe(course => {
            this.tests = this.tests.filter(t => t.id !== test.id);
            this.startPaginator();
            Swal.fire(
              'Deleted:',
              `Test ${test.name} successfully removed from course ${course.name}.`,
              'success'
            );
          });    
        }
      });
    }
}
