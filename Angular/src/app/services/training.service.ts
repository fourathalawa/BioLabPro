import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import {Training} from "../models/training";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  PATH_OF_API = 'http://localhost:8089/BioLabPro/Training';
  constructor(private httpclient: HttpClient) { }

  public getAllTraining() : Observable<Training[]>{
    return this.httpclient.get<Training[]>(this.PATH_OF_API + '/getAllTraining');
}
deleteTraining(training:Training){
  return this.httpclient.delete(this.PATH_OF_API+"/delete-training/"+training.trainingId)
}
addTraining(training:Training){
  return this.httpclient.post(this.PATH_OF_API+"/add-training",training)
}
public getTrainingbyId(id:any) : Observable<Training>{
  return this.httpclient.get<Training>(this.PATH_OF_API + '/getTraining/'+id);
}
public UpdateTrainingG(training:Training) {
  return this.httpclient.put(this.PATH_OF_API + '/update-training/'+training.trainingId,training);
}
public getTrainingbyWord(word:any) : Observable<Training[]>{
  return this.httpclient.get<Training[]>(this.PATH_OF_API + '/getTraining/search/'+word);
}
}
