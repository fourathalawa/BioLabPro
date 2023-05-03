import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointement } from 'src/app/models/Appointement';
import { AppointementService } from 'src/app/services/appointement.service';
import { ChartComponent } from "ng-apexcharts";

@Component({
  selector: 'app-appointment-back',
  templateUrl: './appointment-back.component.html',
  styleUrls: ['./appointment-back.component.css']
})
export class AppointmentBackComponent implements OnInit {
  listappointments: any
  i:number=0;
  p: Appointement = new Appointement();
  constructor(private ac: ActivatedRoute, private ps: AppointementService, private router: Router) { }
  NotReached: any;

  numvalidated: any;
  numnotvalidated: any;
  totalnumber: any;


  ngOnInit(): void {
    this.ps.getallappointmentst().subscribe(res => this.listappointments = res);

    this.ps.allappoints().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.totalnumber = numberOfAppointments;

      },
      (error: any) => {
        console.error(error);
      }
    );
    //validated
    this.ps.allvalidated().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.numvalidated = numberOfAppointments;

      },
      (error: any) => {
        console.error(error);
      }
    );

    //notvalidated
    this.ps.allnotvalidated().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.numnotvalidated = numberOfAppointments;

      },
      (error: any) => {
        console.error(error);
      }
    );

    //notreached
    this.ps.allnotyetreached().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.NotReached = numberOfAppointments;

      },
      (error: any) => {
        console.error(error);
      }
    );

  }

  updateAppointement() {
    this.ps.updateAppointement(this.p).subscribe();
    prompt("Appointement Updated Successfully");
    this.router.navigate(['/back/appointments'])
  }

  deleteAppointment(prodkid: number) {
    this.ps.deleteAppointment(prodkid).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));

  }

  validated(prodkid: number) {
    this.ps.validated(prodkid).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));
    window.location.reload();
  }

  notvalidated(prodkid: number) {
    this.ps.notvalidated(prodkid).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));
    window.location.reload();
  }

  notreached(prodkid: number) {
    this.ps.notyetreached(prodkid).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));
    window.location.reload();
  }

  ban(id: string) {
    this.ps.banpatient(id).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));
    window.location.reload();
  }
  unban(id: string) {
    this.ps.unbanpatient(id).subscribe(() => this.ps.getallappointmentst().subscribe(res => this.listappointments = res));
    window.location.reload();
  }
}
