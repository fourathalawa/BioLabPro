import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gestionuser',
  templateUrl: './gestionuser.component.html',
  styleUrls: ['./gestionuser.component.css']
})
export class GestionuserComponent implements OnInit {

  users: User[] | undefined;
  constructor(private httpClient: HttpClient,
              private userService:UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {

    this.userService.getUsers()
      .subscribe( (users: User[]) => {

        this.users=users ;
      });

  }

  redirectToEdit(identifiant:any):void {
    console.log("test"+identifiant)
    const url = `back/users/edit/${identifiant}`;
    this.router.navigate([url])
  }

  redirectToShow(identifiant:any):void {
    console.log("test"+identifiant)
    const url = `back/users/details/${identifiant}`;
    this.router.navigate([url])
  }



  ban(identifiant:any):void {
    console.log("test"+identifiant)
    this.userService.banUser(identifiant)
      .subscribe( (response) => {

        window.location.reload();
      });
  }

  allow(identifiant:any):void {
    console.log("test"+identifiant)
    this.userService.allowUser(identifiant)
      .subscribe( (response) => {


      });
    window.location.reload();
  }
}
