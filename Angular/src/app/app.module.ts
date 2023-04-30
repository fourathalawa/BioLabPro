import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';

import { FormsModule } from '@angular/forms';
import { BackhomeComponent } from './backoffice/backhome/backhome.component';
import { SidebarComponent } from './backoffice/sidebar/sidebar.component';
import { HeaderbackComponent } from './backoffice/headerback/headerback.component';
import {LoginComponent} from "./components/login/login.component";
import { GestionuserComponent } from './backoffice/gestionuser/gestionuser.component';
import { UserdetailsComponent } from './backoffice/userdetails/userdetails.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule} from "@angular/common/http";
import { EdituserComponent } from './backoffice/edituser/edituser.component';


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
    GestionuserComponent,
    UserdetailsComponent,
    RegisterComponent,
    EdituserComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
