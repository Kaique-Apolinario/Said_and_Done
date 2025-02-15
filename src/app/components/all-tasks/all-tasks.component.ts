import { Component, inject, OnInit } from '@angular/core';
import { Task } from '../../entity/Task';
import { ButtonComponent } from "../button/button.component";
import { TasksService } from '../../services/tasks.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-all-tasks',
  standalone: true,
  templateUrl: './all-tasks.component.html',
  styleUrl: './all-tasks.component.scss',
  imports: [ButtonComponent, HeaderComponent]
})
export class AllTasksComponent implements OnInit{

  private taskService:TasksService = inject(TasksService)

  taskList:Task[] = [];

  ngOnInit(): void {
    this.taskService.getTasks().subscribe((response) => {this.taskList = response});
  }

  deleteTask(task: Task) {
    this.taskService.deleteTask(task).subscribe((response) => {this.taskList = this.taskList.filter(task => task.id != response.id)});
  }
}