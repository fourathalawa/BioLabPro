import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointement } from 'src/app/models/Appointement';
import { AppointementService } from 'src/app/services/appointement.service';

@Component({
  selector: 'app-appointment-back-form',
  templateUrl: './appointment-back-form.component.html',
  styleUrls: ['./appointment-back-form.component.css']
})
export class AppointmentBackFormComponent implements OnInit {

  p:Appointement = new Appointement();
  constructor(private ac:ActivatedRoute,private ps:AppointementService , private router:Router) { }


  ngOnInit(): void {
    let id = this.ac.snapshot.params['id'];
    this.ps.getappbyid(id).subscribe(res=>this.p=res)
  }
  updateAppointement(){
    this.ps.updateAppointement(this.p).subscribe();
    prompt("Appointement Updated Successfully");
    this.router.navigate(['/back/appointments'])
  }
  isValidDate(): boolean {
    const today = new Date();
    const inputDate = new Date(this.p.dateAppointement??"");
    return inputDate > today;
  }
}
