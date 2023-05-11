

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';

import { UserComponent } from './components/user/user.component';
import { AppointementComponent } from './components/appointement/appointement.component';

import { AppointmentFormComponent } from './components/appointment-form/appointment-form.component';
import { BackhomeComponent } from './backoffice/backhome/backhome.component';
import { HeaderbackComponent } from './backoffice/headerback/headerback.component';
import { SidebarComponent } from './backoffice/sidebar/sidebar.component';
import { AppointmentBackComponent } from './components/appointment-back/appointment-back.component';
import { AppointmentBackFormComponent } from './components/appointment-back-form/appointment-back-form.component';
import { DoughnutChartAppointComponent } from './doughnut-chart-appoint/doughnut-chart-appoint.component';
import { InvoiceFrontComponent } from './invoice-front/invoice-front.component';
import { InvoiceBackComponent } from './invoice-back/invoice-back.component';
import { FormBackInvoiceComponent } from './form-back-invoice/form-back-invoice.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartInvoiceComponent } from './components/chart-invoice/chart-invoice.component';
import * as ApexCharts from 'apexcharts';
import { EmailSenderComponent } from './email-sender/email-sender.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    UserComponent,
    AppointementComponent,
    AppointmentFormComponent,
    BackhomeComponent,
    HeaderbackComponent, 
    SidebarComponent, 
    AppointmentBackComponent,
    AppointmentBackFormComponent,
    DoughnutChartAppointComponent,
    InvoiceFrontComponent,
    InvoiceBackComponent,
    FormBackInvoiceComponent,
    ChartInvoiceComponent,
    EmailSenderComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent, HomeComponent, AppointementComponent]
})
export class AppModule { }
