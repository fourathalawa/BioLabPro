import { Component, OnInit } from '@angular/core';
import {Training} from "../models/training";
import {Search} from "../models/search";
import {TrainingService} from "../services/training.service";
import {ActivatedRoute,Router} from "@angular/router";
@Component({
  selector: 'app-searchtraining',
  templateUrl: './searchtraining.component.html',
  styleUrls: ['./searchtraining.component.css']
})
export class SearchtrainingComponent implements OnInit {

  trainings!: Training[];
   searchWord:any;
  constructor(public ts: TrainingService,
    private router: Router,private ac:ActivatedRoute) { }
    delete(t:Training){
      this.ts.deleteTraining(t).subscribe(()=>
      this.ts.getAllTraining().subscribe(res=>
        {this.trainings=res}));
     }
     search(searchWord:any)
     {
      const url = `/getTraining/search/${searchWord}`;
      this.router.navigate([url])
      //location.reload();
     }
  ngOnInit(): void {
    let id = this.ac.snapshot.params['word'];
    this.ts.getTrainingbyWord(id).subscribe(res=>this.trainings=res);
    
    }
update(id:any)
{
  const url = `/updatetraining/${id}`;
  this.router.navigate([url])
}

}
