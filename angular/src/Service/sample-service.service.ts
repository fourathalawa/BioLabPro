import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 import { Observable, map } from 'rxjs';
import { Sample } from 'src/app/models/sample';
 @Injectable({
  providedIn: 'root'
})
export class SampleServiceService {

  readonly API_URL = 'http://localhost:8089/BioLabPro/sample';
  
  constructor(private httpClient: HttpClient) { }

  getAllSample() {
    return this.httpClient.get(`${this.API_URL}/show`)
  }


  getsample(SampleID:number) {
    return this.httpClient.get(`${this.API_URL}/${SampleID}`)
  }
  addSample(sample: Sample): Observable<Sample> {
     return this.httpClient.post<Sample>(`${this.API_URL}/addsample`, sample);
  }
  editSample(Sample : Sample, SampleID:number){
    return this.httpClient.put(`${this.API_URL}/updatesample/${SampleID}`, Sample)
  }
  deleteSample(SampleID : number){
    
    return  this.httpClient.delete(`${this.API_URL}/delete/${SampleID}`)
    
  } 


 
}