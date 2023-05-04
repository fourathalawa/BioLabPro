package tn.esprit.biol.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import tn.esprit.biol.entity.Equipment;

import java.util.List;

public interface IequipmentService {

    public List<Equipment> getAllEquipments();

   // Equipment AddEquipment(Equipment e);

  //  Equipment retrieveEquipment(Integer Id_eq);

   // ResponseEntity<?> deleteEquipment(Integer Id_eq);

    Equipment updateEquipment(Equipment e, Integer eq);

    void sendSterilizationRequest(String to, String f, String l);

    void sendLowStorageRequest(String to, String sub, String bod);

    List<Equipment> getAllEquipmentswithASCSorting(String field);

    List<Equipment> getAllEquipmentswithDESCSorting(String field);

    Page<Equipment> getAllEquipmentswithPagination(Integer offset, Integer pageSize);

    ResponseEntity<?> deleteEq(Integer Id_eq);
    ResponseEntity<?> AddEq(Equipment equipment);
     Equipment getEquipment(Integer Id_eq);

    void Deletep(Integer id_eq);
     int ExpirationThisMonth();
     int totalQuantexpThisMonth();
}
