package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Equipment;

@Repository
public interface EquipmentDao  extends JpaRepository<Equipment,Integer> {
   /* @Query("SELECT e from Equipment  where e.Type=:type and e.Quantity=:Quantity")
    public List<Equipment> findEquipmentByTypeAndQuantity(@Param("type")String Type,@Param("Quantity")Integer Quantity);
*/

  //  Equipment findByType(String title);
}

