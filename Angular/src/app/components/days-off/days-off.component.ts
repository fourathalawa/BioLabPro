import { Component, OnInit  ,ViewChild } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { DaysOff } from 'src/app/models/days-off';
import { User } from 'src/app/models/user';
import { DaysOffService } from 'src/app/services/days-off.service';

@Component({
  selector: 'app-days-off',
  templateUrl: './days-off.component.html',
  styleUrls: ['./days-off.component.css']
})

export class DaysOffComponent implements OnInit {
  @ViewChild('myForm') myForm!: NgForm; // define myForm as type NgForm
  daysOff:DaysOff = new DaysOff();
  constructor(private us:DaysOffService, private _router:Router) {
    
   }
   

   public addDaysOff(): void {
    console.log('Days Off:', this.daysOff);
     // set the user property of the daysOff object
  this.daysOff.user = new User();
  this.daysOff.user.id = "07998550"; // replace with the actual user ID
  
    // get today's date and convert it to a Date object
    const today = new Date();
    const todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
  
    // get the start date and end date from the form and convert them to Date objects
    const startDate = new Date(this.daysOff.startDate);
    const endDate = new Date(this.daysOff.endDate);
  
    // check if the start date is after today's date and the end date is after the start date
    if (startDate <= todayDate || endDate <= startDate) {
      alert('Please enter valid dates.');
      return;
    }
  
    // continue with form submission if the dates are valid
    if (this.myForm.valid) {
      this.us.addDaysOff(this.daysOff).subscribe(
        () => {
          alert('Your request has been successfully submitted.');
          this._router.navigateByUrl('/home');
        },
        (error) => {
          alert('Your request has been successfully submitted.');
          this._router.navigateByUrl('/home');
        }
      );
    }
  }

  ngOnInit(): void {
  }

}