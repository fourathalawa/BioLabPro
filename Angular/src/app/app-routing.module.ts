import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import {BackhomeComponent} from "./backoffice/backhome/backhome.component";
import {LoginComponent} from "./components/login/login.component";


const routes: Routes = [
  {path:"login", component:LoginComponent},
   {path:"", redirectTo:"/login", pathMatch:"full"},
   {path:"home", component:HomeComponent},
  {path:"back", component:BackhomeComponent},
   {path:"**", redirectTo:"/login", pathMatch:"full"},








];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
