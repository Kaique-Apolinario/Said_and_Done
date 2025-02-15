import { Component, EventEmitter, HostListener, inject, Output, ElementRef } from '@angular/core';
import { ButtonComponent } from "../button/button.component";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TasksService } from '../../services/tasks.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Task } from '../../entity/Task';

@Component({
  selector: 'app-create-update',
  imports: [ReactiveFormsModule, ButtonComponent, FormsModule, RouterLink],
  standalone: true,
  templateUrl: './create-update.component.html',
  styleUrl: './create-update.component.scss',
})
export class CreateUpdateComponent {

  private taskService:TasksService = inject(TasksService);
  private router:Router = inject(Router);

  addTaskForms:string = "";
  
  sendTaskName(){
    this.taskService.addTasks({"name": this.addTaskForms, "finished": false}).subscribe();
    this.router.navigateByUrl("/");
    
  }
}