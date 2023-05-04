import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';

import { UserService } from '../services/user.service';
import { userAuthService } from '../services/user-auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private userAuthService: userAuthService,
    private router: Router,
    private userService: UserService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    console.log("method executed");
    if (this.userAuthService.getToken() !== null) {
      const role = route.data['roles'] as Array<string>;
      if (role) {
        console.log('role is'+role)
        const match = this.userService.roleMatch(role);
        if (match) {
          return true;
        } else {

          this.router.navigate([`home`])
          return false;
        }
      }
    }

    this.router.navigate(['/login']);
    return false;
  }
}
