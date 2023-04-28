import { Component, OnInit } from '@angular/core';
import { Equipment } from '../models/equipment/equipment';
import { EquipmentServiceService } from '../services/equipment-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-eq',
  templateUrl: './add-eq.component.html',
  styleUrls: ['./add-eq.component.css']
})
export class AddEqComponent implements OnInit {

  eq : Equipment = new Equipment();

  constructor(private e:EquipmentServiceService, private _router:Router) { }

  ngOnInit(): void {
  }
  addeq(){
    console.log(this.eq);
    this.e.addEq(this.eq).subscribe(()=>this._router.navigateByUrl("/equip"));
    console.log(this.eq.expiration_Date);

}}
