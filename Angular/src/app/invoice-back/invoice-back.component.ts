import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InvoiceService } from '../services/invoice.service';


@Component({
  selector: 'app-invoice-back',
  templateUrl: './invoice-back.component.html',
  styleUrls: ['./invoice-back.component.css']
  
})
export class InvoiceBackComponent implements OnInit {

  idPatient:string="0";
listInvoices : any
Unpayed_AmmountMonth1?:number ;

  constructor(private ac:ActivatedRoute,private ps:InvoiceService , private router:Router) { }

  ngOnInit(): void {
    this.ps.getallInvoices().subscribe(res=>this.listInvoices=res);

    this.ps.Unpayed_AmmountPerMonthYear(4,2023).subscribe(
      (Unpayed_AmmountMonth1: number) => {
        console.log(`Response received: ${Unpayed_AmmountMonth1}`);
        this.Unpayed_AmmountMonth1 = Unpayed_AmmountMonth1;
  
      },
      (error: any) => {
        console.error(error);
      }
  );
  }

  deleteInvoice(prodkid:number){
    this.ps.deleteInvoice(prodkid).subscribe(()=>this.ps.getallInvoices().subscribe(res=>this.listInvoices=res));}

    
   
}
