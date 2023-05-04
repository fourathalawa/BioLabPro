import { Component, OnInit } from '@angular/core';
import { DaysOffService } from 'src/app/services/days-off.service';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import * as nodemailer from 'nodemailer';
import { DaysOff } from 'src/app/models/days-off';

@Component({
  selector: 'app-list-days-off',
  templateUrl: './list-days-off.component.html',
  styleUrls: ['./list-days-off.component.css']
})
export class ListDaysOffComponent implements OnInit {

  listDaysOff: any;
  searchText = '';
  filteredList = [];
  searchForm: FormGroup;

  listArchivedDaysOff: any[] = [];
  listDaysOffCopy: any;

  constructor(private _service:DaysOffService,private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      search: ['']
    });
  }

  ngOnInit(): void {
    this._service.getDaysOff().subscribe(res => {
      console.log(res); 
      this.listDaysOff = res;
      this.listDaysOffCopy = res;
    });
  }

  deleteDaysOff(id:number){
    this._service.deleteDaysOffById(id).subscribe(res=>{this.listDaysOff=res});
  }

  archiveDaysOff(item: any) {
    // Remove the item from listDaysOff
    const index = this.listDaysOff.indexOf(item);
    this.listDaysOff.splice(index, 1);
    
    // Update champ
    this._service.archiveEntity(item.id).subscribe(res=>{this.listDaysOff=res});

    // Reload the page
    window.location.reload();
  }

   
  searchByFirstName(): void {
    if (this.listDaysOff && this.searchForm && this.searchForm.get('searchTerm')) {
      const searchTerm = this.searchForm.get('searchTerm')!.value.toLowerCase();
  
      this.filteredList = this.listDaysOffCopy.filter((item: any) => {
        const firstName = item.user?.userFirstName?.toLowerCase();
        console.log(firstName)
        return firstName && firstName.includes(searchTerm);
      });
    }
  }
  genererPdf(item:DaysOff) {
    
    this._service.genererPdf(item);
    console.log(item);

  }
  




}