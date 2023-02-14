package tn.esprit.biol.service;

import tn.esprit.biol.entity.Equipment;

import java.util.List;

public interface IequipmentService {

    public List<Equipment> getAllEquipments();

    Equipment AddEquipment(Equipment e);

    Equipment retrieveEquipment(String Id_eq);

    void deleteEquipment(String Id_eq);

    Equipment updateEquipment(Equipment e);
}
