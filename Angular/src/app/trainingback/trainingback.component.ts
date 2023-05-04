import { Component, OnInit } from '@angular/core';
import {Training} from "../models/training";
import {Search} from "../models/search";
import {TrainingService} from "../services/training.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trainingback',
  templateUrl: './trainingback.component.html',
  styleUrls: ['./trainingback.component.css']
})

export class TrainingbackComponent implements OnInit {
   trainings!: Training[];
   searchWord:any;
  constructor(public ts: TrainingService,
    private router: Router) { }
    delete(t:Training){
      this.ts.deleteTraining(t).subscribe(()=>
      this.ts.getAllTraining().subscribe(res=>
        {this.trainings=res}));
     }
     search(searchWord:any)
     {
      const url = `/getTraining/search/${searchWord}`;
      this.router.navigate([url])
     }
  ngOnInit(): void {
    this.ts.getAllTraining().subscribe(res=>this.trainings=res);;
  }
update(id:any)
{
  const url = `/updatetraining/${id}`;
  this.router.navigate([url])
}
   
}
