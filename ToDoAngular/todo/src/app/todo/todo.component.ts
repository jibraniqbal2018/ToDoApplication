import { Component, OnInit} from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Task } from '../model/Task';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { ToDoDatasource } from './todo-datasource';
import { TodoService } from '../service/todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
   
  todoFormGroup : FormGroup;
  taskArray : Task[] = [];
  displayedColumns : string[] = ["taskName", "description", "status","delete"]
  
    dataChange : BehaviorSubject<Task[]> = new BehaviorSubject<Task[]>(this.taskArray);
    dataSource = new ToDoDatasource(this.dataChange.asObservable());
  
  constructor(private formBuilder: FormBuilder, private httpService: TodoService ) { }

  ngOnInit() {
    this.todoFormGroup = this.formBuilder.group(
      new Task()    
    )

    this.httpService.getTaskList().subscribe(
      (data) => {
        this.taskArray = data;
        this.dataChange.next(this.taskArray) 
        }
      );

     
  }

  deleteTask(id: number){

    this.httpService.deleteTask(id).subscribe((data)=>{
    this.taskArray = this.taskArray.filter(x => x.id != data.id);
    this.dataChange.next(this.taskArray);  
    this.dataChange.next(this.taskArray);  

    })
  }
    

  markAsCompleted(task:Task){
    task.status = "Completed";
    this.httpService.updateTask(task.id).subscribe((data) =>{
    this.dataChange.next(this.taskArray);  
    console.log(this.taskArray);   
    })

  }
  onSubmit(task : Task){
      this.httpService.saveTak(task).subscribe((data) => {
      this.taskArray.push(data);
      this.dataChange.next(this.taskArray);  
      console.log(this.taskArray);
      console.log("Logging task");
      console.log(task);
  
    } );

  }



}
