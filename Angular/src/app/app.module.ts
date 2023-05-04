import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BackhomeComponent } from './backoffice/backhome/backhome.component';
import { SidebarComponent } from './backoffice/sidebar/sidebar.component';
import { HeaderbackComponent } from './backoffice/headerback/headerback.component';
import {LoginComponent} from "./components/login/login.component";
import {HttpClientModule } from '@angular/common/http';
import { ListDaysOffComponent } from './components/list-days-off/list-days-off.component';
import { DaysOffComponent } from './components/days-off/days-off.component';
import { DaysOffEditComponent } from './components/days-off-edit/days-off-edit.component';
import { MyDialogComponentComponent } from './components/my-dialog-component/my-dialog-component.component';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ArchivedDaysOffComponent } from './components/archived-days-off/archived-days-off.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,

    BackhomeComponent,
    SidebarComponent,
    HeaderbackComponent,
    LoginComponent,
    ListDaysOffComponent,
    DaysOffComponent,
    DaysOffEditComponent,
    ArchivedDaysOffComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
