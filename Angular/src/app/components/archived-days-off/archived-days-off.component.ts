import { Component, OnInit } from '@angular/core';
import { DaysOffService } from 'src/app/services/days-off.service';
import { ListDaysOffComponent } from '../list-days-off/list-days-off.component';
@Component({
  selector: 'app-archived-days-off',
  templateUrl: './archived-days-off.component.html',
  styleUrls: ['./archived-days-off.component.css']
})
export class ArchivedDaysOffComponent implements OnInit {
  listDaysOff:any;
  
  constructor(private _service:DaysOffService) { }

  ngOnInit(): void {
    this._service.getDaysOff().subscribe(res=>{console.log(res); 
      this.listDaysOff=res});

}
deleteDaysOff(id:number){
  this._service.deleteDaysOffById(id).subscribe(res=>{this.listDaysOff=res});
  // Reload the page
  window.location.reload();
}
}
