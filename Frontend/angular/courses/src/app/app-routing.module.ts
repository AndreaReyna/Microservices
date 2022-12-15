import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStudentsComponent } from './components/courses/add-students.component';
import { CoursesFormComponent } from './components/courses/courses-form.component';
import { CoursesComponent } from './components/courses/courses.component';
import { StudentsFormComponent } from './components/students/students-form.component';
import { StudentsComponent } from './components/students/students.component';
import { TestsFormComponent } from './components/tests/tests-form.component';
import { TestsComponent } from './components/tests/tests.component';

const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo: 'courses'},
  {path: 'students', component:StudentsComponent},
  {path: 'students/form', component:StudentsFormComponent},
  {path: 'students/form/:id', component:StudentsFormComponent},
  {path: 'courses', component:CoursesComponent},
  {path: 'courses/form', component:CoursesFormComponent},
  {path: 'courses/form/:id', component:CoursesFormComponent},
  {path: 'tests', component:TestsComponent},
  {path: 'tests/form', component:TestsFormComponent},
  {path: 'tests/form/:id', component:TestsFormComponent},
  {path: 'courses/add-students/:id', component:AddStudentsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
