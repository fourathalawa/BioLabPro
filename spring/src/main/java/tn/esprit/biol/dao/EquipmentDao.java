package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.biol.entity.Equipment;

@Repository
public interface EquipmentDao  extends JpaRepository<Equipment,Integer> {
   /* @Query("SELECT e from Equipment  where e.Type=:type and e.Quantity=:Quantity")
    public List<Equipment> findEquipmentByTypeAndQuantity(@Param("type")String Type,@Param("Quantity")Integer Quantity);
*/

  //  Equipment findByType(String title);


    @Transactional
    @Query(value ="select count(*) from Equipment mp where MONTH(mp.Expiration_Date)=MONTH(CURRENT_DATE()) ",nativeQuery = true)
    int ExpirationThisMonth();
    @Transactional
    @Query(value ="select Sum(mp.Quantity) from Equipment mp where MONTH(mp.Expiration_Date)=MONTH(CURRENT_DATE()) ",nativeQuery = true)
    int totalQuantityExpireThisMonth();
}

