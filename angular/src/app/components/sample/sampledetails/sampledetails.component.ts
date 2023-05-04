import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SampleServiceService } from 'src/Service/sample-service.service';
import { Sample } from 'src/app/models/sample';

@Component({
  selector: 'app-sampledetails',
  templateUrl: './sampledetails.component.html',
  styleUrls: ['./sampledetails.component.css']
})
export class SampledetailsComponent implements OnInit {
  sample: any;
  @Input() sampleID: any;

  constructor(private sampleService: SampleServiceService,private router: ActivatedRoute,private route:Router) { }
  ngOnInit() {
    this.sampleID = this.router.snapshot.params['id'];
    this.sampleService.getsample(this.sampleID).subscribe(res => {
      console.log(res);
      this.sample = res;
    });
  }


  editSample(  sampleID:any ){
    this.sampleService.getsample(sampleID).subscribe(res=>{console.log(res); 
      this.sample=res});
      this.route.navigate(['/sampleedit', sampleID]);  }
  deleteSample(SampleID : any){
    this.sampleService.deleteSample(SampleID).subscribe();      
    this.route.navigate(['/sample']);
    this.sampleService.getAllSample().subscribe(res=>{console.log(res); 
      this.sample=res});

   }

  }