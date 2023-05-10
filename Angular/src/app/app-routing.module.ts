import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import {BackhomeComponent} from "./backoffice/backhome/backhome.component";
import {LoginComponent} from "./components/login/login.component";
import { ListDaysOffComponent } from './components/list-days-off/list-days-off.component';
import { DaysOffComponent } from './components/days-off/days-off.component';
import { DaysOffEditComponent } from './components/days-off-edit/days-off-edit.component';
import { ArchivedDaysOffComponent } from './components/archived-days-off/archived-days-off.component';
import { CalendarComponent } from './calendar/calendar.component';
import { HistoryComponent } from './history/history.component';


const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"", redirectTo:"/login", pathMatch:"full"},
  {path:"home", component:HomeComponent},
  {path:"back", component:BackhomeComponent},
  {path:"listdaysOff", component:ListDaysOffComponent},
  {path:"daysOffEdit/:id", component:DaysOffEditComponent},
  {path:"daysOff", component:DaysOffComponent},
  {path:"archiveDaysOff", component:ArchivedDaysOffComponent},
  {path:"garde", component:CalendarComponent},
  { path: 'history', component: HistoryComponent },


  {path:"**", redirectTo:"/login", pathMatch:"full"},








];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
