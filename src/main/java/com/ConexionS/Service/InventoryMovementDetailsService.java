package com.ConexionS.Service;

import com.ConexionS.Entities.InventoryMovementDetail;
import com.ConexionS.Repository.InventoryMovementDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryMovementDetailsService {

    @Autowired
    private InventoryMovementDetailRepository inventoryMovementDetailRepository;


    public InventoryMovementDetail createInventoryMovementDetail(InventoryMovementDetail inventoryMovementDetail) {
        return inventoryMovementDetailRepository.save(inventoryMovementDetail);
    }

    public List<InventoryMovementDetail> getAllInventoryMovementDetails() {
        return inventoryMovementDetailRepository.findAll();
    }

    public Optional<InventoryMovementDetail> getInventoryMovementDetailById(Integer id) {
        return inventoryMovementDetailRepository.findById(id);
    }
}
