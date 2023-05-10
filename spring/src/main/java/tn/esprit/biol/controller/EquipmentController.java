package tn.esprit.biol.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Equipment;
import tn.esprit.biol.service.IequipmentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    IequipmentService equipmentService;

    // http://localhost:8089/kaddem/equipe/retrieve-all-equipments
    @GetMapping("/retrieve-all-equipments")
    public List<Equipment> getAllEquipments() {
        List<Equipment> listEquipment = equipmentService.getAllEquipments();
        return listEquipment;
    }
    // http://localhost:8089/kaddem/equipe/retrieve-equipment/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    public Equipment retrieveEquipment(@PathVariable("Id_eq") String Id_eq) {
        return equipmentService.retrieveEquipment(Id_eq);
    }
    @PostMapping("/AddEquipment")
    public Equipment AddEquipment(@RequestBody Equipment e) {
        Equipment equipement = equipmentService.AddEquipment(e);
        return equipement;
    }
    // http://localhost:8089/kaddem/equipe/remove-equipe/1
    @DeleteMapping("/deleteEquipment/{Id_eq}")
    public void deleteEquipment(@PathVariable("Id_eq") String Id_eq) {

        equipmentService.deleteEquipment(Id_eq);
    }
    // http://localhost:8089/kaddem/equipe/update-equipe
    @PutMapping("/update-equipe")
    public Equipment updateEtudiant(@RequestBody Equipment e) {
        Equipment equipment= equipmentService.updateEquipment(e);
        return equipment;
    }
}
