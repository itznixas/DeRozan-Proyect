package com.ConexionS.Controller;

import com.ConexionS.Entities.InventoryMovement;
import com.ConexionS.Service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @PostMapping("/add/movement")
    public ResponseEntity<InventoryMovement> createInventoryMovement(@RequestBody InventoryMovement inventoryMovement) {
        InventoryMovement saveInventoryMovement = inventoryMovementService.createInventoryMovement(inventoryMovement);
        return new ResponseEntity<>(saveInventoryMovement, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movements")
    public ResponseEntity<List<InventoryMovement>> getAllInventoryMovement() {
        List<InventoryMovement> inventoryMovement = inventoryMovementService.getAllInventoryMovements();
        return new ResponseEntity<>(inventoryMovement, HttpStatus.OK);
    }

    @GetMapping("/get-movement/{id}")
    public ResponseEntity<InventoryMovement> getInventoryMovementById(@PathVariable Integer id) {
        Optional<InventoryMovement> inventoryMovement = inventoryMovementService.getInventoryMovementById(id);

        return inventoryMovement.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
