package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.EquipmentDao;
import tn.esprit.biol.entity.Equipment;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService implements IequipmentService{
    @Autowired
    private EquipmentDao EqRepo;
    @Override
    public List<Equipment> getAllEquipments() {
        List<Equipment> equipment = new ArrayList<>();
        EqRepo.findAll().forEach(equipment::add);
        return equipment;
    }

    @Override
    public Equipment AddEquipment(Equipment e) {
        return EqRepo.save(e);
    }

    @Override
    public Equipment retrieveEquipment(String Id_eq) {
        return EqRepo.findById(Id_eq).get();
    }

    @Override
    public void deleteEquipment(String Id_eq) {
        EqRepo.deleteById(Id_eq);

    }

    @Override
    public Equipment updateEquipment(Equipment e) {
        return EqRepo.save(e);
    }

}
