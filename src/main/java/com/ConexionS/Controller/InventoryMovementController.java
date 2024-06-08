package com.ConexionS.Controller;

import com.ConexionS.Entities.InventoryMovement;
import com.ConexionS.Entities.InventoryMovementDetail;
import com.ConexionS.Service.InventoryMovementDetailsService;
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

    @Autowired
    private InventoryMovementDetailsService inventoryMovementDetailsService;

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

    @PutMapping("/add-details/{id}")
    public ResponseEntity<InventoryMovementDetail> createInventoryMovementDetail(@RequestBody InventoryMovementDetail inventoryMovementDetail, @PathVariable Integer id) {
        InventoryMovementDetail saveInventoryMovementDetail = inventoryMovementDetailsService.createInventoryMovementDetail(inventoryMovementDetail);
        return new ResponseEntity<>(saveInventoryMovementDetail, HttpStatus.CREATED);
    }

    @GetMapping("/get-details")
    public ResponseEntity<List<InventoryMovementDetail>> getAllInventoryMovementDetails() {
        List<InventoryMovementDetail> inventoryMovementDetail = inventoryMovementDetailsService.getAllInventoryMovementDetails();
        return new ResponseEntity<>(inventoryMovementDetail, HttpStatus.OK);
    }

    @GetMapping("/get-detail/{id}")
    public ResponseEntity<InventoryMovementDetail> getInventoryMovementDetailById(@PathVariable Integer id) {
        Optional<InventoryMovementDetail> inventoryMovementDetail = inventoryMovementDetailsService.getInventoryMovementDetailById(id);

        return inventoryMovementDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
