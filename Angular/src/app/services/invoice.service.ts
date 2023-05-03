import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Invoice } from '../models/Invoice';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  invoiceURL : string ="http://localhost:8081/BioLabPro/Invoice/";
  constructor(private myHttp : HttpClient) { }

  addInvoice(Invoice:Invoice){
    return this.myHttp.post(this.invoiceURL+"addInvoice/",Invoice)
  }
  getallInvoices():Observable<Invoice>{
    return this.myHttp.get<Invoice>(this.invoiceURL+"retreiveAllInvoices/");
  }

  deleteInvoice(id:number){

    return this.myHttp.delete(this.invoiceURL+"deleteInvoice/"+id);
  }
  updateInvoice(Invoice: Invoice):Observable<Invoice>{
    return this.myHttp.put<Invoice>(this.invoiceURL+"updateInvoice/"+Invoice.idInvoice,Invoice);
	}

  getInvoicebyid(id:number):Observable<Invoice>{
    return this.myHttp.get<Invoice>(this.invoiceURL+"retreiveInvoice/"+id);
  }

  getallInvoicesByPatientId(idp:string):Observable<Invoice>{
    return this.myHttp.get<Invoice>(this.invoiceURL+"getInvoicesByidPatien/"+idp);
  }
  addTest(idp:string):Observable<Invoice>{
    return this.myHttp.get<Invoice>(this.invoiceURL+"getInvoicesByidPatien/"+idp);
  }

  downloadPDF(url: string): Observable<Blob> {
    const options = { responseType: 'blob' as 'json' };
    return this.myHttp
   .get<Blob>(url, options)
   .pipe(map(res => new Blob([res], { type: 'application/pdf' })));
 }



AmountPayedperPatient(id:string){
  return this.myHttp.get<number>(this.invoiceURL+"AmountPayedperPatient/"+id);
}

AmountnotPayedperPatient(id:string){
  return this.myHttp.get<number>(this.invoiceURL+"AmountnotPayedperPatient/"+id);
}
AmounttotalperPatient(id:string){
  return this.myHttp.get<number>(this.invoiceURL+"AmounttotalperPatient/"+id);
}

Unpayed_AmmountPerMonthYear(month:number,year:number){
  return this.myHttp.get<number>(this.invoiceURL+"Unpayed_AmmountPerMonthYear/"+year+"/"+month);
}

}
