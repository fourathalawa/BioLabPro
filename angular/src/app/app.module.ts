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
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SampleServiceService } from 'src/Service/sample-service.service';
import { SampleComponent } from './components/sample/sample.component';
import { SampledetailsComponent } from './components/sample/sampledetails/sampledetails.component';
import { SampleeditComponent } from './components/sample/sampleedit/sampleedit.component';
import { CreatesampleComponent } from './components/sample/createsample/createsample.component';
import { SampleresultComponent } from './components/sampleresult/sampleresult.component';
import { FilterPipe } from './components/sample/filter.pipe';
 

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
    SampleComponent,
    SampledetailsComponent,
    SampleeditComponent,
    CreatesampleComponent,
    SampleresultComponent,
    FilterPipe,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
