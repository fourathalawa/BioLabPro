import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointement } from '../models/Appointement';


@Injectable({
  providedIn: 'root'
})
export class AppointementService {

  appointmentURL : string ="http://localhost:8081/BioLabPro/Appointement/";
  constructor(private myHttp : HttpClient) { }

  addAppoint(Appointement:Appointement){
    return this.myHttp.post(this.appointmentURL+"addAppointement/",Appointement)
  }
 
  getallappointmentstbyidPatient(id:string):Observable<Appointement>{
    return this.myHttp.get<Appointement>(this.appointmentURL+"AppointementsdByPatient/"+id);
  }

  getallappointmentst():Observable<Appointement>{
    return this.myHttp.get<Appointement>(this.appointmentURL+"retrieveAppointements/");
  }

  deleteAppointment(id:number){

    return this.myHttp.delete(this.appointmentURL+"deleteAppointement/"+id);
  }

  getappbyid(id:number):Observable<Appointement>{
    return this.myHttp.get<Appointement>(this.appointmentURL+"retrieveAppointement/"+id);
  }

  updateAppointement(Appointement: Appointement):Observable<Appointement>{
    return this.myHttp.put<Appointement>(this.appointmentURL+"updateAppointement/"+Appointement.idAppointement,Appointement);
	}

  validated(id: number){
    return this.myHttp.put<Appointement>(this.appointmentURL+"validated/"+id,Appointement);
	}
  notvalidated(id: number){
    return this.myHttp.put<Appointement>(this.appointmentURL+"not_validated/"+id,Appointement);
	}
  notyetreached(id: number){
    return this.myHttp.put<Appointement>(this.appointmentURL+"not_yet_reached/"+id,Appointement);
	}
//
allappoints(){
  return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsThisMonth/");
}
  allvalidated(){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsValidatedThisMonth/");
	}
  allnotvalidated(){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsNotValidatedThisMonth/");
	}
  allnotyetreached(){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsNotYetReachedThisMonth/");
	}
//
  NumberOfAppointementsByPatient(id:string){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsByPatient/"+id);
  }

  NumberOfAppointementsvalidatedByPatient(id:string){
    return this.myHttp.get<number>(this.appointmentURL+"NumlberofAppointmentsValidatedByPatient/"+id);
  }

  NumberOfAppointementsnotvalidatedByPatient(id:string){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsNotValidatedByPatient/"+id);
  }

  NumberOfAppointementsnotreachedByPatient(id:string){
    return this.myHttp.get<number>(this.appointmentURL+"NumberOfAppointementsNotReachedByPatient/"+id);
  }

  banpatient(id:string){
    return this.myHttp.get(this.appointmentURL+"BanPatient/"+id)
  }
  unbanpatient(id:string){
    return this.myHttp.get(this.appointmentURL+"unBanPatient/"+id)
  }



}
