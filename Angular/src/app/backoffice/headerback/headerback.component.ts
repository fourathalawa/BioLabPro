import { Component, OnInit } from '@angular/core';
import {userAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-headerback',
  templateUrl: './headerback.component.html',
  styleUrls: ['./headerback.component.css']
})
export class HeaderbackComponent implements OnInit {

  constructor(private userAuthService:userAuthService,
              private router:Router) { }

  ngOnInit(): void {
  }
  logout() {
    this.userAuthService.clear();
    this.router.navigate(['/home']); // Redirection vers la route 'accueil'
  }
}
