import { Component, inject, OnInit } from '@angular/core';
import { TasksService } from '../../services/tasks.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ButtonComponent } from "../button/button.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Task } from '../../entity/Task';

@Component({
  selector: 'app-update',
  standalone: true,
  imports: [ButtonComponent, ButtonComponent, RouterLink, FormsModule],
  templateUrl: './update.component.html',
  styleUrl: './update.component.scss'
})
export class UpdateComponent implements OnInit{

  private taskService:TasksService = inject(TasksService);
  private router:Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);

  newTask:Task = {
    name: "",
    finished: false

  };

  taskFromId:Task = {
    name: "",
    finished: false
  };


  ngOnInit(): void {
    const taskId = Number(this.route.snapshot.paramMap.get("id"));
    if (taskId) {
      this.taskService.getTaskById(taskId).subscribe(taskFromId => {this.taskFromId = taskFromId});
    }
  }


  updateName(){
    if (!this.newTask.name.trim()) 
      return alert("Oops! Empty name.");
    this.taskService.updateTask(this.taskFromId, this.newTask).subscribe();
    this.router.navigateByUrl("/");
  }
}