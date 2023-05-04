import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
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
  message: string| undefined;
  selectedFiles: any| undefined;
  selectedFile: any| undefined;
  currentFileUpload: any;
  private currentTime!: number;
  progress!: number;

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  imageName: any;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private httpClient: HttpClient,
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

  onUpload() {
    console.log(this.selectedFile);

    // FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile!, this.selectedFile?.name);

    // Make a call to the Spring Boot Application to save the image
    this.httpClient.post(`${'http://localhost:8089/BioLabPro/image/upload/'}${ this.identifiant}`, uploadImageData, {
      headers: this.requestHeader,
    } )
      .subscribe((response) => {
          if ( response === 200) {
            this.message = 'Image uploaded successfully';
          } else {
            this.message = 'Image not uploaded successfully';
          }
        }
      );
  }

  public onFileChanged(event:any) {
    // Select File
    this.selectedFile = event.target.files[0];
  }
}
