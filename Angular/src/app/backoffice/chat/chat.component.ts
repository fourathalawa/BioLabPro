import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AfterViewChecked, Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable,of  } from 'rxjs';


import { FormControl ,FormControlDirective,ReactiveFormsModule} from '@angular/forms';
import { catchError, retry } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';


import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { userAuthService } from 'src/app/services/user-auth.service';
import { Messaggio } from 'src/app/models/messaggio.model';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit , AfterViewChecked {
  url = 'http://localhost:8089/BioLabPro';
  otherUser?: User;
  thisUser: User = JSON.parse(sessionStorage.getItem('user')!);
  channelName?: string;
  socket?: WebSocket;
  stompClient? : Stomp.Client;
  newMessage = new FormControl('');
  messages?: Observable<Array<Messaggio>>;
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private userAuthService: userAuthService,
    private http:HttpClient,
    private el: ElementRef,
    private domSanitizer: DomSanitizer) {}


  ngOnInit(): void {

    this.userService
      .getUserByNickname(this.route.snapshot.paramMap.get('user')!)
      .subscribe((data: any) => {

        this.otherUser = data;


     //   this.otherUser.image=data.image.picByte;
       // console.log(this.otherUser.image+"aaaaaaaaaaa");

        this.connectToChat();
        console.log(this.el)
        this.el.nativeElement.querySelector("#chat").scrollIntoView();
      });
  }

  ngAfterViewChecked(): void {
    this.scrollDown();
  }

  scrollDown(){
    var container = this.el.nativeElement.querySelector("#chat");
    container.scrollTop = container.scrollHeight;
  }

  connectToChat() {
    const headers = { 'Authorization': 'Bearer my-token', 'My-Custom-Header': 'foobar' };
    console.log("gooo");

    const id1 = this.userAuthService.getUserId().replace('"','').replace('"','')!;
    const nick1 = this.userAuthService.getUserName().replace('"','').replace('"','');


    const id2 = this.otherUser?.id!;

    const nick2 = this.otherUser?.userFirstName!;


   if (id1.toLowerCase().localeCompare(id2.toLowerCase())==1) {
    this.channelName = id1 + '&' + id2;
    console.log("id1 "+id1+" superieur à id2 "+id2)
    console.log("channelname "+this.channelName);
  }
  else {
    this.channelName = id2 + '&' + id1;
    console.log("channelname "+this.channelName);
    console.log("id2 "+id2+" superieur à id1"+id1)
  }

    this.loadChat();
    console.log('connecting to chat...');
    this.socket = new SockJS (this.url + '/chat');


    this.stompClient = Stomp.over(this.socket);
    console.log('connecting to chat...'+this.socket);
    this.stompClient.connect (
      {  }
    , (frame) => {

      //func = what to do when connection is established

        console.log('connected to: ' );

        this.stompClient!.subscribe(

         '/topic/messages/' + this.channelName,
         () => {
            console.log('connected');
            this.loadChat();
          }
        );
      }
    , (error?: string | Stomp.Frame) => {
      console.error('error connecting: ' + error);
  });

  }

  sendMsg() {
    const headers = { 'Authorization': 'Bearer my-token', 'My-Custom-Header': 'foobar' };
    if (this.newMessage.value !== '') {
      this.stompClient!.send(
        '/app/chat/' + this.channelName,
        {header : headers},
        JSON.stringify({
          sender: this.userAuthService.getUserName(),
          id: this.userAuthService.getUserId(),
          t_stamp: 'to be defined in server',
          content: this.newMessage.value,
        })
      );
      this.newMessage.setValue('');
    }
  }

  loadChat(){
console.log("error is here")
     const headers = { 'Authorization': 'Bearer my-token', 'My-Custom-Header': 'foobar' };
    this.messages = this.http.post<Array<Messaggio>>(this.url+'/getMessages' ,  this.channelName );
    this.messages.subscribe(data => {
      let mgs:Array<Messaggio> = data;
      mgs.sort((a, b) => (a.ms_id > b.ms_id) ? 1 : -1)
      this.messages = of(mgs);
    })
    console.log(this.messages);
  }

  whenWasItPublished(myTimeStamp: string) {
    const endDate = myTimeStamp.indexOf('-');
    return (
      myTimeStamp.substring(0, endDate) +
      ' at ' +
      myTimeStamp.substring(endDate + 1)
    );
  }

}
