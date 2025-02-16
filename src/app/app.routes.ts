import { Routes } from '@angular/router';
import { AllTasksComponent } from './components/all-tasks/all-tasks.component';
import { CreateComponent } from './components/create/create.component';
import { UpdateComponent } from './components/update/update.component';

export const routes: Routes = [
    { path: 'home',  pathMatch: 'full', component: AllTasksComponent},
    { path: '',   redirectTo: 'home', pathMatch: 'full' },
    { path: 'create',  pathMatch: 'full', component: CreateComponent},
    { path: 'update/:id',  pathMatch: 'full', component: UpdateComponent},
];

