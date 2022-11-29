import { Student } from "./student";
import { Test } from "./test";

export class Course {
    id:number;
    name:string;
    createAt:string;
    students:Student[] = [];
    tests:Test[] = [];
}
