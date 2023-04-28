import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Equipment } from '../models/equipment/equipment';

@Injectable({
  providedIn: 'root'
})
export class EquipmentServiceService {

  readonly API_URL="http://localhost:8089/bio/equipment/";


  constructor(private _http:HttpClient) { }

  getEquipment()  {
    return this._http.get(`${this.API_URL}retrieve-all-equipments`);
    }
    getEquipmentById(id: number): Observable<Equipment>{
      return this._http.get<Equipment>(`${this.API_URL}getEq/${id}`);
    }
    deleteEquipmentById(id :number){
      return this._http.delete(`${this.API_URL}delet/`+id);
    }
    addEq(eq:Equipment){
      return this._http.post<Equipment>(`${this.API_URL}addNewEq/`,eq);
    }
     
  updateEquipment(id: number, equipment: Equipment): Observable<Object>{
    return this._http.put(`${this. API_URL}updateEquipment/`+id, equipment);
  }
}