import { Component } from '@angular/core';
import { ButtonComponent } from "../button/button.component";

@Component({
  selector: 'app-create-update',
  standalone: true,
  templateUrl: './create-update.component.html',
  styleUrl: './create-update.component.scss',
  imports: [ButtonComponent]
})
export class CreateUpdateComponent {

}
