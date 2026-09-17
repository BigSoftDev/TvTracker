import { Routes } from '@angular/router';
import { ShowIndexComponent } from './components/show-index/show-index.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'show-index', component: ShowIndexComponent },
  { path: '**', redirectTo: '' } // Wildcard for undefined routes
];
