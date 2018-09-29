import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Task } from '../model/Task';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private taskListURL = "http://localhost:8080/todo/taskList";
  private saveTaskURL = "http://localhost:8080/todo/saveTask"; 
  private deleteTaskURL = "http://localhost:8080/todo/deleteTask";
  private updateTaskURL = "http://localhost:8080/todo/updateTask";

  constructor(private http: HttpClient) { }

  getTaskList(): Observable<Task[]>{
    return this.http.get<Task[]>(this.taskListURL);
  }

  saveTak(task: Task):Observable<Task>{
   return this.http.post<Task>(this.saveTaskURL,task);

  }

  deleteTask(id: number):Observable<Task>{
    return this.http.delete<Task>(this.deleteTaskURL+"/"+id);

  }

  updateTask(id:number):Observable<number>{
    return this.http.put<number>(this.updateTaskURL+"/"+id,null);

  }
}

