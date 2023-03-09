package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.EquipmentDao;
import tn.esprit.biol.entity.Equipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@EnableScheduling
public class EquipmentService implements IequipmentService{
    @Autowired
    EmailServiceSender es;
    @Autowired
    private EquipmentDao EqRepo;

    @Override
    public List<Equipment> getAllEquipments() {
        List<Equipment> equipment = new ArrayList<>();
        EqRepo.findAll().forEach(equipment::add);
        return equipment;
    }

   /* @Override
    public Equipment AddEquipment(Equipment e) {
        return EqRepo.save(e);
    }*/

    @Override
    public ResponseEntity<?> AddEq(Equipment equipment) {

        if (equipment.getType().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Choose a correct type above 0");
        } else if (equipment.getExpiration_Date() == null || (equipment.getExpiration_Date().isEqual(LocalDate.now())) || equipment.getExpiration_Date().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("DATE NOT ACCEPTABLE");
        } else if (equipment.getSterilization_Date() == null || (equipment.getSterilization_Date().isEqual(LocalDate.now())) || equipment.getSterilization_Date().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("DATE NOT ACCEPTABLE");
        } else if (equipment.getQuantity() == null || equipment.getQuantity() < 1) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Quantity can't be null or less than 1");
        } else {
            EqRepo.save(equipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
        }
    }

    /*@Override
    public Equipment retrieveEquipment(Integer Id_eq) {
        return EqRepo.findById(Id_eq).get();
    }*/

    @Override
    public ResponseEntity<?> deleteEq(Integer Id_eq ) {

        Optional<Equipment> equipment = EqRepo.findById(Id_eq);
        if (!equipment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("there is no such Equipment with this id: " + Id_eq);
        } else {

            EqRepo.deleteById(Id_eq);
            return ResponseEntity.status(HttpStatus.OK).body("Equipment deleted");
        }
    }

    public void Deletep(Integer id){
        EqRepo.deleteById(id);
    }
    @Override
    public Equipment updateEquipment(Equipment e,Integer idEq) {
        Equipment eq = EqRepo.findById(idEq).get();
        eq.setType(e.getType());
        eq.setExpiration_Date(e.getExpiration_Date());
        eq.setQuantity(e.getQuantity());
        eq.setSterilization_Date(e.getSterilization_Date());
        return EqRepo.save(eq);
    }

    @Override
    public void sendSterilizationRequest(String to,String f,String l) {
        // Envoi de l'email de demande de congé
        es.sendEmail(to,f,l);
    }
    @Override

    public void sendLowStorageRequest(String to,String sub,String bod) {
        // Envoi de l'email de demande de congé
        es.sendEmail(to,sub,bod);
    }
    @Override

    public List<Equipment> getAllEquipmentswithASCSorting(String field){
        return EqRepo.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    @Override

    public List<Equipment> getAllEquipmentswithDESCSorting(String field){
        return EqRepo.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    @Override

    public Page<Equipment> getAllEquipmentswithPagination(Integer offset,Integer pageSize){
        Page<Equipment> equip= EqRepo.findAll(PageRequest.of(offset, pageSize));
        return equip;
    }

    public Equipment getEquipment(Integer Id_eq) {
        return EqRepo.findById(Id_eq).get();

    }


    public int ExpirationThisMonth() {
        return EqRepo.ExpirationThisMonth();

    }
    public int totalQuantexpThisMonth() {
        return EqRepo.totalQuantityExpireThisMonth();
    }



}
