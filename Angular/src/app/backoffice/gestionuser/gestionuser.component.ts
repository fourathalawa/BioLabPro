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
  isAlert : boolean |undefined;

  idBan : string |undefined;
  constructor(private httpClient: HttpClient,
              private userService:UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.retrieveUsers();
    this.isAlert=false;
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

  redirectToChat(identifiant:any):void {
    console.log("test"+identifiant)
    const url = `chat/${identifiant}`;
    this.router.navigate([url])
  }

  ban(identifiant:any):void {
    console.log("test"+identifiant)
    this.isAlert=true;
    this.idBan=identifiant;
  }

  ban2():void {
    console.log("test"+this.idBan)
    this.userService.banUser(this.idBan)
      .subscribe( (response) => {

        window.location.reload();
      });
  }

  hideNotif():void {
  this.isAlert=false;
  }

  allow(identifiant:any):void {
    console.log("test"+identifiant)
    this.userService.allowUser(identifiant)
      .subscribe( (response) => {


      });
    window.location.reload();
  }
}
