import { Component, OnInit } from '@angular/core';
import { AppointementService } from '../../services/appointement.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointement } from '../../models/Appointement';

@Component({
  selector: 'app-appointement',
  templateUrl: './appointement.component.html',
  styleUrls: ['./appointement.component.css'],
  //template: '<div class="number-increment" [attr.data-value]="count">{{count}}</div>'

})
export class AppointementComponent implements OnInit {
  count = 0;
  listappointments : any
  p:Appointement = new Appointement();
  constructor(private ac:ActivatedRoute,private ps:AppointementService , private router:Router) { }

  NotReached:number =0;
  validated:number=0;
  notvalidated:number=0;
  totalnumber: number=0;

  //integration 
  idPatient:string="0";
  ngOnInit(): void {
    this.ps.getallappointmentstbyidPatient(this.idPatient).subscribe(res=>this.listappointments=res);
    
    //total
    this.ps.NumberOfAppointementsByPatient(this.idPatient).subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.totalnumber = numberOfAppointments;
      },
      (error: any) => {
        console.error(error);
      }
    );
    //validated
    this.ps.NumberOfAppointementsvalidatedByPatient(this.idPatient).subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.validated = numberOfAppointments;
      },
      (error: any) => {
        console.error(error);
      }
    );

     //notvalidated
     this.ps.NumberOfAppointementsnotvalidatedByPatient(this.idPatient).subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.notvalidated = numberOfAppointments;
      },
      (error: any) => {
        console.error(error);
      }
    );

    //notreached
    this.ps.NumberOfAppointementsnotreachedByPatient(this.idPatient).subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.NotReached = numberOfAppointments;
      },
      (error: any) => {
        console.error(error);
      }
    );

    

    
  }

  deleteAppointement(prodkid:number){
    this.ps.deleteAppointment(prodkid).subscribe(()=>this.ps.getallappointmentstbyidPatient(this.idPatient).subscribe(res=>this.listappointments=res));}

  
}
