import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-email-sender',
  templateUrl: './email-sender.component.html',
  styleUrls: ['./email-sender.component.css']
})
export class EmailSenderComponent implements OnInit {
  emailForm: FormGroup ;

  constructor(private fb: FormBuilder, private http: HttpClient) { 
    this.emailForm = this.fb.group({
      email: '',
      subject: '',
      message: ''
    });
  }

  ngOnInit(): void {
  }

  sendEmail() {
    const emailData = this.emailForm.value;
    this.http.post('http://localhost:8081/BioLabPro/Invoice/sendMailReclamation/',emailData.email,+'/'+emailData.subject,).subscribe(response => {
      console.log(response);
    });
  }
}
