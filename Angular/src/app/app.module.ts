import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';

import { FormsModule } from '@angular/forms';
import { BackhomeComponent } from './backoffice/backhome/backhome.component';
import { SidebarComponent } from './backoffice/sidebar/sidebar.component';
import { HeaderbackComponent } from './backoffice/headerback/headerback.component';
import {LoginComponent} from "./components/login/login.component";
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { EquipmentComponentComponent } from './equipment-component/equipment-component.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { AddEqComponent } from './add-eq/add-eq.component';
import { UpdateEqComponent } from './update-eq/update-eq.component';
import{MatSortModule  } from '@angular/material/sort';
import{MatTableModule  } from '@angular/material/table';
import{MatPaginatorModule  } from '@angular/material/paginator';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import{ NgxPaginationModule  } from 'ngx-pagination';


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
    EquipmentComponentComponent,
    EquipmentDetailsComponent,
    AddEqComponent,
    UpdateEqComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,HttpClientModule,MatFormFieldModule,MatSortModule,MatTableModule,MatPaginatorModule, NoopAnimationsModule,Ng2SearchPipeModule,Ng2OrderModule,NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
