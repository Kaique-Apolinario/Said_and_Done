import { Routes } from '@angular/router';
import { AllTasksComponent } from './components/all-tasks/all-tasks.component';
import { CreateUpdateComponent } from './components/create-update/create-update.component';

export const routes: Routes = [
    { path: 'home',  pathMatch: 'full', component: AllTasksComponent},
    { path: '',   redirectTo: 'home', pathMatch: 'full' },
    { path: 'create',  pathMatch: 'full', component: CreateUpdateComponent},
];

