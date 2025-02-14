import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../entity/Task';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private http: HttpClient) { }

  private apiUrl: string = "http://localhost:8080/";

  getTasks():Observable<Task[]>{
    return this.http.get<Task[]>(this.apiUrl);
  } 

  
}
