package com.ConexionS.Repository;

import com.ConexionS.Entities.InventoryMovementDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementDetailRepository extends JpaRepository<InventoryMovementDetail, Integer> {

}
