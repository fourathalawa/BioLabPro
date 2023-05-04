import { Component, OnInit } from '@angular/core';
import {Training} from "../models/training";
import {TrainingService} from "../services/training.service";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-addtraining',
  templateUrl: './addtraining.component.html',
  styleUrls: ['./addtraining.component.css']
})
export class AddtrainingComponent implements OnInit {
  t:Training = new Training();

  constructor(public ts: TrainingService,
    private router: Router,private ac:ActivatedRoute) { }
    isStartDateValid(): boolean {
      const today = new Date();
      const date =new Date(this.t.training_startdate);
      console.log(today,date,today<date);
      return date.getTime() > today.getTime();
    }
    isendDateValid(): boolean {
      const today = new Date(this.t.training_enddate);
      const date =new Date(this.t.training_startdate);
      console.log(today,date,today>date);
      return date.getTime() <today.getTime();
    }
    addTraining(){
     
     
      this.ts.addTraining(this.t).subscribe(()=>this.router.navigateByUrl("/trainingback"));
      
      this.t=new Training();
      
    }
  ngOnInit(): void {
  }
 
}
