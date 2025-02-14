import { Component, inject, OnInit } from '@angular/core';
import { Task } from '../../entity/Task';
import { TasksService } from '../../services/tasks.service';
import { ButtonComponent } from "../button/button.component";

@Component({
  selector: 'app-all-tasks',
  standalone: true,
  templateUrl: './all-tasks.component.html',
  styleUrl: './all-tasks.component.scss',
  imports: [ButtonComponent]
})
export class AllTasksComponent{



  taskList: Task[] = [{ "id": 0, "name": "Zero", "finished": false }, {
    "id": 1, "name": "One", "finished": false
  }, {
    "id": 2, "name": "Two~~~~~~~~~~~~~~~~DAAA", "finished": false
  }, {
    "id": 3, "name": "Three", "finished": false
  }, {
    "id": 4, "name": "Four", "finished": false
  }, {
    "id": 5, "name": "Five", "finished": false
  },{
    "id": 6, "name": "Six", "finished": false
  },{
    "id": 7, "name": "Seven", "finished": false
  },{
    "id": 8, "name": "Eight", "finished": false
  },{
    "id": 9, "name": "Nine", "finished": false
  }
  ];

}
