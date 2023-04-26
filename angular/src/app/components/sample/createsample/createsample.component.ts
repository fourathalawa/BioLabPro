import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  constructor(private sampleService: SampleServiceService,private router: ActivatedRoute) { }
  ngOnInit() { 
    
  }
   
  addSample(sample: Sample): void {
    this.sample.Dateofwithdrawl = sample.Dateofwithdrawl;
    this.sample.Urgency = sample.Urgency;
    this.sampleService.addSample(this.sample)
      .subscribe();
      console.log(sample.Dateofwithdrawl);
      console.log(sample.Urgency);
    this.sample = new Sample();
  
}
}
