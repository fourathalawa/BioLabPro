import { Component, OnInit } from '@angular/core';
import {Training} from "../models/training";
import {TrainingService} from "../services/training.service";
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-updatetraining',
  templateUrl: './updatetraining.component.html',
  styleUrls: ['./updatetraining.component.css']
})
export class UpdatetrainingComponent implements OnInit {
  t:Training = new Training();
  constructor(public ts: TrainingService,
    private router: Router,private ac:ActivatedRoute) { }
    Update(){
      this.ts.UpdateTrainingG(this.t).subscribe(
        ()=>this.router.navigateByUrl("/trainingback"));
    }
     
  isStartDateValid(): boolean {
    const today = new Date();
    const date =new Date(this.t.training_startdate);
    return date.getTime() > today.getTime();
  }
  isendDateValid(): boolean {
    const today = new Date(this.t.training_enddate);
    const date =new Date(this.t.training_startdate);
    console.log(today,date,today>date);
    return date.getTime() <today.getTime();
  }
  ngOnInit(): void {
    let id = this.ac.snapshot.params['identifiant'];
  this.ts.getTrainingbyId(id).subscribe(res=>
    this.t=res);

  }


}
