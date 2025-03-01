import { Component, EventEmitter, HostListener, inject, Output, ElementRef } from '@angular/core';
import { ButtonComponent } from "../button/button.component";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TasksService } from '../../services/tasks.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Task } from '../../entity/Task';

@Component({
  selector: 'app-create',
  imports: [ReactiveFormsModule, ButtonComponent, FormsModule, RouterLink, FormsModule],
  standalone: true,
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss',
})
export class CreateComponent {

  private taskService:TasksService = inject(TasksService);
  private router:Router = inject(Router);

  addTaskForms:string = "";
  
  sendTaskName(){
    if (!this.addTaskForms.trim()) 
      return alert("Oops! Empty name.");
    this.taskService.addTasks({"name": this.addTaskForms, "finished": false}).subscribe( () => 
      this.router.navigateByUrl("/"));
    
  }
}