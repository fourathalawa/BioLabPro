import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackhomeComponent } from './backoffice/backhome/backhome.component';
import { AppointementComponent } from './components/appointement/appointement.component';
import { AppointmentBackFormComponent } from './components/appointment-back-form/appointment-back-form.component';
import { AppointmentBackComponent } from './components/appointment-back/appointment-back.component';
import { AppointmentFormComponent } from './components/appointment-form/appointment-form.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FormBackInvoiceComponent } from './form-back-invoice/form-back-invoice.component';
import { InvoiceBackComponent } from './invoice-back/invoice-back.component';
import { InvoiceFrontComponent } from './invoice-front/invoice-front.component';
import { TestType } from './models/TestType';
const routes: Routes = [
  
  {path:"invoices", component:InvoiceFrontComponent},
  {path:"appointments", component:AppointementComponent},
  {path:"appointmentsForm/:id", component:AppointmentFormComponent},
  {path:"appointmentsForm", component:AppointmentFormComponent},
  {path:"back/appointmentsbackForm/:id", component:AppointmentBackFormComponent},
  {path:"back/appointments", component:AppointmentBackComponent},
  {path:"back/invoices", component:InvoiceBackComponent},
  {path:"back/addinvoice/:id",component:FormBackInvoiceComponent},
  {path:"back/addinvoice",component:FormBackInvoiceComponent},
  {path:"login", component:LoginComponent},
  //{ path: "C:/PdfInvoices/:id"+".pdf", component: PdfViewerComponentComponent },
   {path:"", redirectTo:"/login", pathMatch:"full"},
   {path:"home", component:HomeComponent},
   {path:"back", component:BackhomeComponent},
   {path:"**", redirectTo:"/login", pathMatch:"full"},
   
  
   //{ path: 'home', component: HomeComponent }



  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
