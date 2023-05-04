import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

import {BackhomeComponent} from "./backoffice/backhome/backhome.component";
import {LoginComponent} from "./components/login/login.component";
import { SampleComponent } from './components/sample/sample.component';
import { SampledetailsComponent } from './components/sample/sampledetails/sampledetails.component';
import { SampleeditComponent } from './components/sample/sampleedit/sampleedit.component';
import { CreatesampleComponent } from './components/sample/createsample/createsample.component';
import { SampleresultComponent } from './components/sampleresult/sampleresult.component';


const routes: Routes = [
  {path:"login", component:LoginComponent},
    {path:"home", component:HomeComponent},
   {path:"sample", component:SampleComponent},
   {path:"result", component:SampleresultComponent},
   { path: 'sample/:id', component: SampledetailsComponent },
   { path: 'sampleedit/:id', component: SampleeditComponent },
   { path: 'createsample', component: CreatesampleComponent },

  {path:"back", component:BackhomeComponent},
   {path:"**", redirectTo:"/login", pathMatch:"full"},








];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
