import { Routes } from '@angular/router';
import { ShowIndexComponent } from './components/show-index/show-index.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'show-index', component: ShowIndexComponent },
  { path: 'admin', component: AdminComponent },
  { path: '**', redirectTo: '' } // Wildcard for undefined routes
];
