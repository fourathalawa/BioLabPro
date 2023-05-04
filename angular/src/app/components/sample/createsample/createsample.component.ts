import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { SampleServiceService } from 'src/Service/sample-service.service';
import { Sample } from 'src/app/models/sample';

@Component({
  selector: 'app-createsample',
  templateUrl: './createsample.component.html',
  styleUrls: ['./createsample.component.css']
})

export class CreatesampleComponent implements OnInit {
  sample: Sample = new Sample();
  p:any;
  constructor(private sampleService: SampleServiceService,private router: Router) { }
  ngOnInit() { 
    
  }
   
  backfront(){
    this.router.navigate(['/sample']); 
   }
  addSample(sample: Sample): void {
    this.sample.dateofwithdrawl = sample.dateofwithdrawl;
    this.sample.urgency = sample.urgency;
    this.sample.userid = sample.userid;
    this.sampleService.addSample(this.sample)
      .subscribe();
      console.log(sample.dateofwithdrawl);
      console.log(sample.urgency);
    this.sample = new Sample();
  
}
}