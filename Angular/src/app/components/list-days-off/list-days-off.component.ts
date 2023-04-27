import { Component, OnInit } from '@angular/core';
import { DaysOffService } from 'src/app/services/days-off.service';

@Component({
  selector: 'app-list-days-off',
  templateUrl: './list-days-off.component.html',
  styleUrls: ['./list-days-off.component.css']
})
export class ListDaysOffComponent implements OnInit {

  
  listDaysOff :any;
  constructor(private _service:DaysOffService) { }

  ngOnInit(): void {
	  this._service.getDaysOff().subscribe(res=>{console.log(res); 
   this.listDaysOff=res});
  }
  deleteDaysOff(id:number){
    this._service.deleteDaysOffById(id).subscribe(res=>{this.listDaysOff=res});
  }


}
