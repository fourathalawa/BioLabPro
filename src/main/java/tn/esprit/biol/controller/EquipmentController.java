package tn.esprit.biol.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Equipment;
import tn.esprit.biol.service.IequipmentService;

import java.time.LocalDate;
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
    @GetMapping("/retrieve-equipement/{Id_eq}")
    public Equipment retrieveEquipment(@PathVariable("Id_eq") Integer Id_eq) {
        return equipmentService.retrieveEquipment(Id_eq);
    }
    @PostMapping({"/addNewEq"})
    public ResponseEntity<?> addNewEq(@RequestBody Equipment equipment)
    {
        return equipmentService.AddEq(equipment);
    }
    @PostMapping("/AddEquipment")
    /*public Equipment AddEquipment(@RequestBody Equipment e) {
        Equipment equipement = equipmentService.AddEquipment(e);
        return equipement;
    }*/
   /* // http://localhost:8089/kaddem/equipe/remove-equipe/1
    @DeleteMapping("/deleteEquipment/{Id_eq}")
    public void deleteEquipment(@PathVariable("Id_eq") Integer Id_eq) {

        equipmentService.deleteEquipment(Id_eq);
    }*/

    @DeleteMapping({"/delete/{Id_eq}"})
    public ResponseEntity<?> DeleteEq(@PathVariable("Id_eq") Integer Id_eq)
    {
        return equipmentService.deleteEq(Id_eq);
    }

    // http://localhost:8089/kaddem/equipe/update-equipe
    @PutMapping("/updateEquipment/{id}")
    public Equipment updateEquipment(@RequestBody Equipment e,@PathVariable("id") Integer id) {
        Equipment equipment= equipmentService.updateEquipment(e,id);
        return equipment;
    }

    @PostMapping({"/Sterilization"})

    public void SendRequest(@RequestBody Equipment equipment)
    {
        if(LocalDate.now().equals(equipment.getSterilization_Date()))
        equipmentService.sendSterilizationRequest("hannachifedi12@gmail.com","ALERT STERILIZATION","STERILIZATION DATE IS HERE !!!!!!");

    }

    @PostMapping({"/Storage"})

    public void SendStorageRequest(@RequestBody Equipment equipment)
    {
        if(equipment.getQuantity()<=10)
            equipmentService.sendLowStorageRequest("hannachifedi12@gmail.com","LOW STORAGE ALERT","QUANTITY IS LOW !!!!!!");

    }

    @GetMapping("/A/{field}")
    public List<Equipment> getEquipmentswithASCSorting(@PathVariable String field) {
        List<Equipment> listEquipmentAS = equipmentService.getAllEquipmentswithASCSorting(field);
        return listEquipmentAS;
    }

    @GetMapping("/D/{field}")
    public List<Equipment> getEquipmentswithDESCSorting(@PathVariable String field) {
        List<Equipment> listEquipmentDESC = equipmentService.getAllEquipmentswithDESCSorting(field);
        return listEquipmentDESC;
    }

    @GetMapping("/pagination/{offset}/{pagesize}")
    public Page<Equipment> getEquipmentswithPag(@PathVariable Integer offset, @PathVariable Integer pagesize) {
        Page<Equipment> listEquipmentPG = equipmentService.getAllEquipmentswithPagination(offset, pagesize);
        return listEquipmentPG;
    }

    @GetMapping("/getEq/{id}")
    Equipment getEq(@PathVariable Integer id) {
        return equipmentService.getEquipment(id);
    }






}
