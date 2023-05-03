import { TestType } from "./TestType";

export class  Invoice{
    idInvoice:number=0;
    dateInvoice?:Date;
    totalAmount:number=0;
    idPatient:string="0";
    statusPayment:number=0;
    descreptionInvoice?:string
    TestList?: TestType;

    
}
