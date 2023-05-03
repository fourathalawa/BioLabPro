import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointementService } from 'src/app/services/appointement.service';
import { Appointement } from 'src/app/models/Appointement';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  
  p:Appointement = new Appointement();
  constructor(private ac:ActivatedRoute,private ps:AppointementService , private router:Router) { }

  ngOnInit(): void {
  }
 

  isValidDate(): boolean {
    const today = new Date();
    const inputDate = new Date(this.p.dateAppointement??"");
    return inputDate > today;
  }
  addAppointement(){
    console.log(this.p);
    prompt("Appointment Saved Succesfully");
    this.ps.addAppoint(this.p).subscribe(()=>this.router.navigate(['/appointments']));
  }

  updateAppointement(){
    this.ps.updateAppointement(this.p).subscribe();
    prompt("Appointement Updated Successfully");
    this.router.navigate(['/appointments'])
  }
}
