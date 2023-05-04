import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DaysOff } from 'src/app/models/days-off';
import { DaysOffService } from 'src/app/services/days-off.service';

@Component({
  selector: 'app-days-off-edit',
  templateUrl: './days-off-edit.component.html',
  styleUrls: ['./days-off-edit.component.css']
})
export class DaysOffEditComponent implements OnInit {
daysOff !:DaysOff
  feedback !:any
  constructor(private route: ActivatedRoute,
  private router: Router,
  private _service : DaysOffService) {
}
ngOnInit (){ 
  this.route.paramMap.subscribe(next=>this._service.getDaysById(Number(next.get( 'id'))).subscribe(res=>{this.daysOff=res}), 
  error=>console.log(error)); } 

  update(){ 
    this._service.updateDaysOff(this.daysOff.id,this.daysOff).subscribe();} 

  cancel() {
    this.router.navigate(['/listdaysOff']);
  }

  accept() {
    this.daysOff.request = true;
    this.daysOff.etat = 'Accepted';

    this.update();
  }
  
  refuse() {
    this.daysOff.request = true;
    this.daysOff.etat = 'Refused';
    this.update();
  }
  
}
