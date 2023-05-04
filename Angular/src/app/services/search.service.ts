import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import {Search} from "../models/search";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  PATH_OF_API = 'http://localhost:8089/BioLabPro/search';
  constructor(private httpclient: HttpClient) { }

}
