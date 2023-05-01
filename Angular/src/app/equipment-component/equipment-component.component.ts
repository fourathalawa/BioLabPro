import { Component, OnInit ,ViewChild} from '@angular/core';
import { EquipmentServiceService } from '../services/equipment-service.service';
import { Router } from '@angular/router';
import { Equipment } from '../models/equipment/equipment';

@Component({
  selector: 'app-equipment-component',
  templateUrl: './equipment-component.component.html',
  styleUrls: ['./equipment-component.component.css']
})
export class EquipmentComponentComponent implements OnInit {
  p: number = 1;
  type:any;
  
  listEquipment:any = [];

  constructor(private _service:EquipmentServiceService,
    private router: Router) { }

  ngOnInit(): void {
    this._service.getEquipment().subscribe(res=>{console.log(res);
      this.listEquipment=res
   });
      
  }

  deleteEquipment(id:number){
        this._service.deleteEquipmentById(id).subscribe(()=>this._service.getEquipment()
    .subscribe(res=>{this.listEquipment=res}));
      }

    EquipmentDetails(id: number){
      this.router.navigate(['equipment-details', id]);
    }
    updateEquipment(id: number){
      this.router.navigate(['update-eq', id]);
    }
    Search(){
      if(this.type == ""){
        this.ngOnInit();
      }else{
        this.listEquipment = this.listEquipment.filter((res:any)=>{
          return res.type.toLocaleLowerCase().match(this.type.toLocaleLowerCase());
        })
      }
    }
    key: string ='id';
    reverse:boolean=false;
    sort(key: string) {
     this.key=key;
      this.reverse=!this.reverse;
    }

  

  }