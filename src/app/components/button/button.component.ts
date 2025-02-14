import { Component, input } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-button',
  standalone: true,
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
  label = input('');
}
