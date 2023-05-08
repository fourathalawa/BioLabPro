import { Component, OnInit } from '@angular/core';
import { SampleServiceService } from 'src/Service/sample-service.service';
import { Sample } from 'src/app/models/sample';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.css'] 
})



export class SampleComponent implements OnInit {
id:any;
  Samples: any;
  searchTerm: string = '';
  filteredSamples: any;


  constructor(private SampleService:SampleServiceService ,private router: Router) { }

  ngOnInit(): void { 
     
      this.SampleService.getAllSample().subscribe(res => {
        console.log(res);
        this.Samples = res;
        this.filteredSamples = this.Samples;
      });
    
  }    showSampleDetails(sampleID: number) {
    this.SampleService.getsample(sampleID).subscribe(res=>{console.log(res); 
    this.Samples=res});
    this.router.navigate(['/sample/', sampleID]);

}

search(sampleID: number) {
  this.SampleService.getsample(sampleID).subscribe(res=>{console.log(res); 
  this.Samples=res});
  this.router.navigate(['/sample/', sampleID]);

}
/* addSample(sample: Sample){
    this.SampleService.addSample(sample).subscribe(() => {
      this.getAllSample();
      this.form = false;
    });
  }
*/

  editSample(  sampleID:any ){
    this.SampleService.getsample(sampleID).subscribe(res=>{console.log(res); 
      this.Samples=res});
      this.router.navigate(['/sampleedit', sampleID]);  }
  deleteSample(SampleID : any){
    this.SampleService.deleteSample(SampleID).subscribe();      
    this.router.navigate(['/sample']);
    this.SampleService.getAllSample().subscribe(res=>{console.log(res); 
      this.Samples=res});
   }/*
  */


   createsample(id: String){
    id='1';
    this.router.navigate(['/createsample']); 
   }
   backfront(){
    this.router.navigate(['/sample']); 
   }
   gotoresults(){
    this.router.navigate(['/result']); 
   }

  
}