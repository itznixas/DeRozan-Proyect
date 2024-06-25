package com.ConexionS.Service;

import com.ConexionS.Entities.InventoryMovement;
import com.ConexionS.Repository.InventoryMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovement createInventoryMovement(InventoryMovement inventoryMovement) {
        return inventoryMovementRepository.save(inventoryMovement);
    }

    public List<InventoryMovement> getAllInventoryMovements() {
        return inventoryMovementRepository.findAll();
    }

    public Optional<InventoryMovement> getInventoryMovementById(Integer id) {
        return inventoryMovementRepository.findById(id);
    }

}
