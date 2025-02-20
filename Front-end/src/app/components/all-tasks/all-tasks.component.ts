import { Component, inject, OnInit } from '@angular/core';
import { Task } from '../../entity/Task';
import { TasksService } from '../../services/tasks.service';
import { HeaderComponent } from '../header/header.component';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-all-tasks',
  standalone: true,
  templateUrl: './all-tasks.component.html',
  styleUrl: './all-tasks.component.scss',
  imports: [HeaderComponent, RouterLink]
})
export class AllTasksComponent implements OnInit{

  private taskService:TasksService = inject(TasksService)
  private router:Router = inject(Router)

  taskList:Task[] = [{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false},{name: "AAAAAAAAAAAAAAAAAAAAAAA", finished: false}];


  ngOnInit(): void {
    this.taskService.getTasks().subscribe((response) => {this.taskList = response});
  }


  deleteTask(task: Task) {
    this.taskService.deleteTask(task).subscribe(() => {this.taskList = this.taskList.filter(taskInList => taskInList != task)});
  }

  updateTask(task: Task){
    this.router.navigate(["/update/", task.id]);
  }

}