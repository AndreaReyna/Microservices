import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentsComponent } from './components/students/students.component';
import { CoursesComponent } from './components/courses/courses.component';
import { TestsComponent } from './components/tests/tests.component';
import { LayoutModule } from './layout/layout.module';
import { StudentsFormComponent } from './components/students/students-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatPaginatorModule} from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoursesFormComponent } from './components/courses/courses-form.component';
import { TestsFormComponent } from './components/tests/tests-form.component';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatDialogModule} from '@angular/material/dialog';
import {MatExpansionModule} from '@angular/material/expansion';
import { AddStudentsComponent } from './components/courses/add-students.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { AddTestsComponent } from './components/courses/add-tests.component';
import { AnswerTestComponent } from './components/students/answer-test.component';
import { AnswerTestModalComponent } from './components/students/answer-test-modal.component';
@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    CoursesComponent,
    TestsComponent,
    StudentsFormComponent,
    CoursesFormComponent,
    TestsFormComponent,
    AddStudentsComponent,
    AddTestsComponent,
    AnswerTestComponent,
    AnswerTestModalComponent
  ],
  entryComponents:[AnswerTestModalComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatCardModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
