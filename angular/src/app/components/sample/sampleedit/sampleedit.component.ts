import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { SampleServiceService } from 'src/Service/sample-service.service';
import { Sample } from 'src/app/models/sample';

@Component({
  selector: 'app-sampleedit',
  templateUrl: './sampleedit.component.html',
  styleUrls: ['./sampleedit.component.css']
})
export class SampleeditComponent implements OnInit {
  sample: any;
  sampleID: any;

  constructor(private sampleService: SampleServiceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sampleID = this.route.snapshot.params['id'];
    this.sampleService.getsample(this.sampleID).subscribe(res => {
      console.log(res);
      this.sample = res;
    });
  }
  
  editSample(sample: Sample, sampleID: number) {
    // update the sample object with the values from the parameter
     this.sample.Dateofwithdrawl = sample.dateofwithdrawl;
    this.sample.Urgency = sample.urgency;
    //this.sample.userid = sample.userid;

    this.sampleService.editSample(this.sample, this.sampleID).subscribe(res => {
      console.log(res);
      // navigate back to the sample list after successful edit
      this.router.navigate(['/sample']);
      console.log(this.sample.dateofwithdrawl);
      console.log(this.sample.sampleID);
      console.log(    this.sample.urgency);
    });
  }
  
}