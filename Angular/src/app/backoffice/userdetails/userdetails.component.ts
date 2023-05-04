import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-userdetails',
  templateUrl: './userdetails.component.html',
  styleUrls: ['./userdetails.component.css']
})
export class UserdetailsComponent implements OnInit {
  user: User = new User();

  constructor(private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getUser(this.route.snapshot.paramMap.get('identifiant'));
  }

  getUser(id:any): void {

    this.userService.getUserDetails(id)
      .subscribe(
        (user: User) => {
          this.user=user ;

        });
  }

}
