import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User = new User();
  email: any;
  password: any;
  constructor(private _router:Router) { }

  ngOnInit(): void {
  }
  loginUser()
  {
    // Récupérer les valeurs saisies par l'utilisateur
     const email = this.user.email;
     const password = this.user.password;
 
  
    if (email == 'user' && password == 'user') {
      localStorage.setItem('User', "['user','user']");
      this._router.navigateByUrl('home');
    } else {
      alert('Email or password is incorrect');
    }
  }
  

}
