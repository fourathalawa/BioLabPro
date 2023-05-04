import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DaysOff } from 'src/app/models/days-off';
import { User } from 'src/app/models/user';
import { DaysOffService } from 'src/app/services/days-off.service';

@Component({
  selector: 'app-days-off',
  templateUrl: './days-off.component.html',
  styleUrls: ['./days-off.component.css']
})
export class DaysOffComponent implements OnInit {
  daysOff:DaysOff = new DaysOff();
  constructor(private us:DaysOffService, private _router:Router) {
    
   }

   addDaysOff(){
    if (!this.daysOff) {
      // Si l'objet 'daysOff' n'est pas initialisé, initialiser un nouvel objet 'DaysOff'
      this.daysOff = new DaysOff();
    }
    console.log('Days Off:', this.daysOff);
    this.us.addDaysOff(this.daysOff).subscribe(()=>this._router.navigateByUrl("/home/listuser"));
  }

  ngOnInit(): void {
  }

}
