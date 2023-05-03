import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { from } from 'rxjs';
import { Invoice } from '../models/Invoice';
import { TestType } from '../models/TestType';
import { InvoiceService } from '../services/invoice.service';

@Component({
  selector: 'app-form-back-invoice',
  templateUrl: './form-back-invoice.component.html',
  styleUrls: ['./form-back-invoice.component.css']
})
export class FormBackInvoiceComponent implements OnInit {
  index:number=1;
  listInvoices : any
  p:Invoice = new Invoice();
  test: TestType = new TestType();  
  invoiceForm:any
  
  isLoading = false;

  constructor(private ac:ActivatedRoute,private ps:InvoiceService , private fb: FormBuilder, private router:Router) { }
  ngOnInit(): void {
    let id = this.ac.snapshot.params['id'];
    this.ps.getInvoicebyid(id).subscribe(res=>this.p=res)
    
    this.invoiceForm = this.fb.group({
      idInvoice: [0],
      dateInvoice: [null],
      totalAmount: [0],
      idPatient: ['0'],
      statusPayment: [0],
      descreptionInvoice: [null],
      testList: this.fb.array([this.initTest()])
    });
  }

  
  onSubmit(): void {
    // Handle form submission here
  }
  get testList(): FormArray {
    return this.invoiceForm.get('testList') as FormArray;
  }
  removeTest(index: number): void {
    this.testList.removeAt(index);
  }
  addTest() {
    const control = this.invoiceForm.controls.testList as FormArray;
    control.push(this.initTest());
  }
  
  initTest() {
    return this.fb.group({
      testName: ''
    });
  }
  async addInvoice() {
    this.isLoading = true
    from(this.ps.addInvoice(this.p)).subscribe((data) => {this.isLoading = false; this.router.navigate(['/back/invoices'])});

  }
  updateInvoice(){
    console.log(this.p);
    from(this.ps.updateInvoice(this.p)).subscribe(()=>this.router.navigate(['/back/invoices']));
    prompt("Invoice updated Succesfully");
  }

}
