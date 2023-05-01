import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class userAuthService {
  constructor() {}

  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    // @ts-ignore
    return JSON.parse(localStorage.getItem('roles'));
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    // @ts-ignore
    return localStorage.getItem('jwtToken');
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }

  public getUserName(): string {
    // @ts-ignore
    return localStorage.getItem('nomUser');
  }

  public getUserId(): string {
    // @ts-ignore
    return localStorage.getItem('id');
  }

  public getUserId2(): any {
    return localStorage.getItem('id');
  }
}
