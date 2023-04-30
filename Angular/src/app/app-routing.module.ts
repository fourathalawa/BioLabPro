import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import {BackhomeComponent} from "./backoffice/backhome/backhome.component";
import {LoginComponent} from "./components/login/login.component";
import {GestionuserComponent} from "./backoffice/gestionuser/gestionuser.component";
import {UserdetailsComponent} from "./backoffice/userdetails/userdetails.component";
import {RegisterComponent} from "./register/register.component";
import {EdituserComponent} from "./backoffice/edituser/edituser.component";
import {AuthGuard} from "./authentification/auth.guard";


const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
   {path:"", redirectTo:"/login", pathMatch:"full"},
  {
    path: "back/users", component: GestionuserComponent,
    canActivate: [AuthGuard],
    data: {roles: ['HeadSupervisor']}
  },
  {path:"back/users/details/:identifiant", component:UserdetailsComponent,
  canActivate: [AuthGuard],
  data: {roles: ['HeadSupervisor']}
},
  {
    path: "back/users/edit/:identifiant", component: EdituserComponent,
    canActivate: [AuthGuard],
    data: {roles: ['HeadSupervisor']}
  },
   {path:"home", component:HomeComponent},
  {
    path: "back", component: BackhomeComponent,
    canActivate: [AuthGuard],
    data: {roles: ['HeadSupervisor']}
  },
   {path:"**", redirectTo:"/login", pathMatch:"full"},








];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
