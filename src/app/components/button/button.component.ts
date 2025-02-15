import { Component, input } from '@angular/core';
import { AppComponent } from '../../app.component';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {
  label = input('');

}
