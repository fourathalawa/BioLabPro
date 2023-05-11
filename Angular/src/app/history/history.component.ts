import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DaysOff } from 'src/app/models/days-off';
import { DaysOffService } from 'src/app/services/days-off.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  userId!: string; // Utilisez le type de données correct (string) pour correspondre à votre implémentation du service
  daysOff!: DaysOff[];
  daysOffList: DaysOff[] = [];

  constructor(private _daysOffService: DaysOffService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id')!;
    this.getDaysOff();
  }
  
  getDaysOff() {
    this._daysOffService.getDaysOffByUserId(this.userId).subscribe(
      data => {
        this.daysOffList = data;
      },
      error => {
        console.log(error);
      }
    )
  }
}