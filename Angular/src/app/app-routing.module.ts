import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import {BackhomeComponent} from "./backoffice/backhome/backhome.component";
import {LoginComponent} from "./components/login/login.component";
import { EquipmentComponentComponent } from './equipment-component/equipment-component.component';
import { AddEqComponent } from './add-eq/add-eq.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { UpdateEqComponent } from './update-eq/update-eq.component';


const routes: Routes = [
  {path:"login", component:LoginComponent},

  {path: 'update-eq/:id', component: UpdateEqComponent},
  {path: 'equipment-details/:id', component: EquipmentDetailsComponent},
  {path:"addeq", component: AddEqComponent},
  {path:"equip", component:EquipmentComponentComponent},



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
