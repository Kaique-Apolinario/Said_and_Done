import { Component, inject, OnInit, signal} from '@angular/core';
import { Task } from '../../entity/Task';
import { TasksService } from '../../services/tasks.service';
import { HeaderComponent } from '../header/header.component';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-all-tasks',
  standalone: true,
  templateUrl: './all-tasks.component.html',
  styleUrl: './all-tasks.component.scss',
  imports: [HeaderComponent, RouterLink, CommonModule]
})
export class AllTasksComponent implements OnInit{

  private taskService:TasksService = inject(TasksService)
  private router:Router = inject(Router)

  taskList = signal<Task[]>([]);


  ngOnInit(): void {
    this.taskService.getTasks().subscribe((response) => {this.taskList.set(response)});
  }

  deleteTask(task: Task) {
    this.taskService.deleteTask(task).subscribe(() => {this.taskList.set(this.taskList().filter((taskFromList) => taskFromList !== task))})
  }
  
  updateTask(task: Task){
      this.router.navigate(["/update", task.id]).catch(e => {
        alert("An error occurred. Sorry! Try reloading the page"),
        console.error("Failed to found a update route to this task.");
      })
    }

  toggleStatus(task: Task){
    const temporaryTask: Task = task;
    temporaryTask.finished = !temporaryTask.finished;
    this.taskService.updateTask(task, temporaryTask).subscribe();
  }
}