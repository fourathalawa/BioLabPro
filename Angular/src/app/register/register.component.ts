import { Component, OnInit } from '@angular/core';
import {User} from "../models/user";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  cin = '';
  firstName = '';
  lastName = '';
  email = '';
  tel = '';

  password = '';
  passwordV = '';
  adress = '';

  checked:Boolean=false;

  constructor(public userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  signup() {

    this.user.id = this.cin;


    this.user.userFirstName = this.firstName;

    this.user.userLastName = this.lastName;

    this.user.userPassword = this.password;

    this.user.tel = this.tel;

    this.user.email = this.email;

    this.user.adress = this.adress;



    if (this.user.id.length == 0 || this.user.userFirstName.length == 0 || this.user.userLastName.length == 0 ||
      this.user.userPassword.length == 0 || this.user.tel.length == 0 || this.user.email.length == 0
      || this.user.adress.length == 0
    ) {

      window.alert("fill all the blanks");
    }
    else{

      this.userService.create(this.user).subscribe(()=> this.router.navigate(['/login']));


    }

  }
}
