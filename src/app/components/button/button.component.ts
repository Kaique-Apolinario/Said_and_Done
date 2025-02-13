import { Component, input } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-button',
  imports: [],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
  label = input('');
}
