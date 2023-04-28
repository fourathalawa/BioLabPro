import { Component, OnInit } from '@angular/core';
import { Equipment } from '../models/equipment/equipment';
import { ActivatedRoute } from '@angular/router';
import { EquipmentServiceService } from '../services/equipment-service.service';

@Component({
  selector: 'app-equipment-details',
  templateUrl: './equipment-details.component.html',
  styleUrls: ['./equipment-details.component.css']
})
export class EquipmentDetailsComponent implements OnInit {
  id!: number;
  equipment: Equipment = new Equipment;

  constructor(private route: ActivatedRoute, private equipmentService: EquipmentServiceService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.equipment = new Equipment();
    this.equipmentService.getEquipmentById(this.id).subscribe( data => {
      this.equipment = data;
    });
  }
  }
