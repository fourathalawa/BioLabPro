import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { I } from '@fullcalendar/core/internal-common';
import { Observable } from 'rxjs';
import { SampleResult } from 'src/app/models/sample-result';

@Injectable({
  providedIn: 'root'
})
export class SampleResultServiceService {

  readonly API_URL = 'http://localhost:8089/BioLabPro/sampleResult';
  
  constructor(private httpClient: HttpClient) { }

  getresults() {
    return this.httpClient.get(`${this.API_URL}/get`)
  }


  getresult(resultid:number) {
    return this.httpClient.get(`${this.API_URL}/searchBySampleID/${resultid}`)
  }

 

  addSample(SampleResult: SampleResult): Observable<SampleResult> {
     return this.httpClient.post<SampleResult>(`${this.API_URL}/save`, SampleResult);
  }
  editSample(SampleResult : SampleResult, resultid:number){
    return this.httpClient.put(`${this.API_URL}/${resultid}`, SampleResult)
  }
  deleteSample(resultid : number){
    
    return  this.httpClient.delete(`${this.API_URL}/${resultid}`)
    
  } 
  qrcode(resultid:number) {
    return this.httpClient.get(`${this.API_URL}/generateQRCodeForSampleResult/${resultid}`)
  console.log(resultid);  
  }
  pdf(SampleResult:any) {
    return this.httpClient.get(`${this.API_URL}/pdf/${SampleResult}`)

  }
  sms(number:String) {
    return this.httpClient.get(`${this.API_URL}/sendSMS/${number}`)

  }
}