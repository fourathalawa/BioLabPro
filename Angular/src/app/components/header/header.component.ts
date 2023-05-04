import { Component, OnInit } from '@angular/core';
import {userAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  idCurrentUser: string | null | undefined;

  constructor(private userAuthService: userAuthService,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.idCurrentUser = this.userAuthService.getUserId()
  }

  logout() {
    this.userAuthService.clear();
    this.router.navigate(['/home']); // Redirection vers la route 'accueil'
    this.idCurrentUser = null;
  }

  goToBack() {

    this.router.navigate(['/back']); // Redirection vers la route 'accueil'

  }

  roleMatch(): boolean {
    return this.userService.roleMatch(['HeadSupervisor'])
  }
}
