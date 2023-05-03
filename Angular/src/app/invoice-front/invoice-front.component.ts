import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Invoice } from '../models/Invoice';
import { TestType } from '../models/TestType';
import { AppointementService } from '../services/appointement.service';
import { InvoiceService } from '../services/invoice.service';


@Component({
  selector: 'app-invoice-front',
  templateUrl: './invoice-front.component.html',
  styleUrls: ['./invoice-front.component.css']
})
export class InvoiceFrontComponent implements OnInit {
  //integration 
  idPatient:string="0";
  listInvoices : any
  p:Invoice = new Invoice();
  x:TestType = new TestType();
window: any;
url: any;
  httpClient: any;
pdfUrl: any;

payedAmount:any;
unpayedAmount:any;
total:any;
  payPalConfig:any;
  showSuccess: any;

  constructor(private ac:ActivatedRoute,private ps:InvoiceService , private router:Router ) { }

  viewPdf(id: number) {
    this.router.navigate(['pdf-viewer', id]);
  }





  ngOnInit(): void {
    
    this.ps.getallInvoicesByPatientId(this.idPatient).subscribe(res=>this.listInvoices=res);

    this.ps.AmountPayedperPatient(this.idPatient).subscribe(
      (payedAmount: number) => {
        console.log(`Response received: ${payedAmount}`);
        this.payedAmount = payedAmount;
  
      },
      (error: any) => {
        console.error(error);
      })

      this.ps.AmountnotPayedperPatient(this.idPatient).subscribe(
        (unpayedAmount: number) => {
          console.log(`Response received: ${unpayedAmount}`);
          this.unpayedAmount = unpayedAmount;
    
        },
        (error: any) => {
          console.error(error);
        }
    );

    this.ps.AmounttotalperPatient(this.idPatient).subscribe(
      (total: number) => {
        console.log(`Response received: ${total}`);
        this.total = total;
  
      },
      (error: any) => {
        console.error(error);
      }
  );
    
  }
  openPDF(pdfUrl: any) {
   this.ps.downloadPDF(pdfUrl).subscribe(res => {
    const fileURL = URL.createObjectURL(res);
    window.open(fileURL, '_blank');
  });
  }

  
  deleteInvoice(prodkid:number){
    this.ps.deleteInvoice(prodkid).subscribe(()=>this.ps.getallInvoicesByPatientId(this.idPatient).subscribe(res=>this.listInvoices=res));}

    unpaid(prodkid:number){
      this.ps.AmountnotPayedperPatient(this.idPatient).subscribe();}
  
      payed(prodkid:number){
        this.ps.AmountPayedperPatient(this.idPatient).subscribe();}

     
       
      }
      

