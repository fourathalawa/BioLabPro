import { Component, OnInit } from '@angular/core';
import { Equipment } from '../models/equipment/equipment';
import { EquipmentServiceService } from '../services/equipment-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-eq',
  templateUrl: './update-eq.component.html',
  styleUrls: ['./update-eq.component.css']
})
export class UpdateEqComponent implements OnInit {
  id!: number;
  equipment: Equipment = new Equipment();

  constructor(private equipmentService: EquipmentServiceService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']; 
     this.equipmentService.getEquipmentById(this.id).subscribe(data => {
       this.equipment = data;
      }, error => console.log(error));
     }

     onSubmit(){
      this.equipmentService.updateEquipment(this.id, this.equipment).subscribe( data =>{
        this.goToEquipmentList();
      }
      , error => console.log(error));
    }
    Retour(){
      this.router.navigate(['/equip']);
    }


  goToEquipmentList(){
    this.router.navigate(['/equip']);}


}