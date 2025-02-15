import { Component, EventEmitter, HostListener, inject, Output, ElementRef } from '@angular/core';
import { ButtonComponent } from "../button/button.component";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TasksService } from '../../services/tasks.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-update',
  imports: [ReactiveFormsModule, ButtonComponent, FormsModule],
  standalone: true,
  templateUrl: './create-update.component.html',
  styleUrl: './create-update.component.scss',
})
export class CreateUpdateComponent {

  private taskService:TasksService = inject(TasksService);
  private router:Router = inject(Router);

  addTaskForms:string = "";

  @Output() taskNameHERE = new EventEmitter<string>();
  
  sendTaskName(){
    this.taskNameHERE.emit(this.addTaskForms);
    console.log(this.addTaskForms);
  }

}
