import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Task } from '../entity/Task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  private http:HttpClient = inject(HttpClient);

  private apiUrl: string = "http://localhost:8080/";

  getTasks():Observable<Task[]>{
    return this.http.get<Task[]>(this.apiUrl);
  } 

  addTasks(task:Task):Observable<Task>{
    return this.http.post<Task>(this.apiUrl + "save", task);
  }

  deleteTask(task:Task):Observable<Task>{
    return this.http.delete<Task>(this.apiUrl + task.id);
  }
}
