import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  identifiant!: any;
  user: User = new User();
  email: string | undefined;
  tel: string | undefined;
  adress: string | undefined;

  userFirstName: string | undefined;

  userLastName: string | undefined;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private httpclient: HttpClient,
              public userService: UserService) {
  }

  ngOnInit(): void {
    this.identifiant = this.route.snapshot.paramMap.get('identifiant');
    this.retrieveUser();
  }

  retrieveUser(): void {

    this.identifiant = this.route.snapshot.paramMap.get('identifiant');

    this.userService.getUserDetails(this.identifiant)
      .subscribe(
        (user: User) => {
          /*user.forEach(u => {
            this.users[0].image = 'data:image/jpeg;base64,' + u.image.picByte;
            i++
          });*/

          this.email = user.email;
          this.tel = user.tel;
          this.adress = user.adress;
          this.userFirstName = user.userFirstName;
          this.userLastName = user.userLastName;

          console.log(this.user);

        });

  }


  update() {
    this.user.tel=this.tel;

    this.user.email=this.email;
    this.user.adress=this.adress;
 console.log(this.user+"testedit")

    this.userService.update(this.user,this.identifiant)
      .subscribe((response) => {
        console.log(response+"response")
        if (response.status === 200) {
          console.log('update successfully');


        } else {
          console.log('erreur update');
        }
        window.location.reload();
      });
  }
}
