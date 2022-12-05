import { Generic } from "./generic";
import { Question } from "./question";
import { Subject } from "./subject";

export class Test implements Generic{
    id:number;
    name:string;
    createAt:string;
    questions: Question[] = [];
    subject: Subject[] = [];
    answered:boolean;
}
