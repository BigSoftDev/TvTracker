import { Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { UserSelectionComponent } from './components/user-selection/user-selection.component';
import { MyShowsComponent } from './components/my-shows/my-shows.component';
import { NewShowsComponent } from './components/new-shows/new-shows.component';

import { authGuard } from './guards/auth.guard';

export const routes: Routes = [

  {
    path: '',
    component: HomeComponent,
    canActivate: [authGuard]
  },

  {
    path: 'new-shows',
    component: NewShowsComponent,
    canActivate: [authGuard]
  },

  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [authGuard]
  },

  {
    path: 'user-selection',
    component: UserSelectionComponent
  },

  {
    path: 'my-shows',
    component: MyShowsComponent,
    canActivate: [authGuard]
  },

  {
    path: '**',
    redirectTo: ''
  }

];