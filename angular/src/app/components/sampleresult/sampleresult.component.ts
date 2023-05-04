import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SampleResultServiceService } from 'src/Service/sample-result-service.service';

@Component({
  selector: 'app-sampleresult',
  templateUrl: './sampleresult.component.html',
  styleUrls: ['./sampleresult.component.css']
})
export class SampleresultComponent implements OnInit {
  results:any;
  constructor( private resultservice:SampleResultServiceService,private router: Router) { } 

  ngOnInit(): void {
    this.resultservice.getresults().subscribe(res=>{console.log(res); 
      this.results=res});
  }
  qr(resultid : any){
    this.resultservice.qrcode(resultid).subscribe();      
    this.router.navigate(['/result']);
     console.log(resultid)
   }
}
