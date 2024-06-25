package com.ConexionS.Controller;

import com.ConexionS.Entities.ShippingAddress;
import com.ConexionS.Service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/shipping-addresses")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @PostMapping("/add-shipping-address")
    public ResponseEntity<ShippingAddress> createShippingAddress(@RequestBody ShippingAddress shippingAddress) {
        ShippingAddress savedShippingAddress = shippingAddressService.createShippingAddress(shippingAddress);
        return new ResponseEntity<>(savedShippingAddress, HttpStatus.CREATED);
    }

    @GetMapping("/get-shipping-address/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable Long id) {
        Optional<ShippingAddress> shippingAddress = shippingAddressService.getShippingAddressById(id);

        if(shippingAddress.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update-shipping-address/{id}")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable Long id, @RequestBody(required = false) ShippingAddress shippingAddressDetails) {
        Optional<ShippingAddress> shippingAddressOptional = shippingAddressService.getShippingAddressById(id);

        if (shippingAddressOptional.isPresent()) {
            ShippingAddress shippingAddress = shippingAddressOptional.get();

            if (shippingAddressDetails != null && shippingAddressDetails.getCountry() != null && shippingAddressDetails.getDepartment() != null && shippingAddressDetails.getMunicipality() != null && shippingAddressDetails.getPostalCode() != null && shippingAddressDetails.getAddress() != null && shippingAddressDetails.getBackupAddress() != null) {

                shippingAddress.setCountry(shippingAddressDetails.getCountry());
                shippingAddress.setDepartment(shippingAddressDetails.getDepartment());
                shippingAddress.setMunicipality(shippingAddressDetails.getMunicipality());
                shippingAddress.setPostalCode(shippingAddressDetails.getPostalCode());
                shippingAddress.setAddress(shippingAddressDetails.getAddress());
                shippingAddress.setBackupAddress(shippingAddressDetails.getBackupAddress());

                ShippingAddress updatedShippingAddress = shippingAddressService.updateShippingAddress(shippingAddress);
                return new ResponseEntity<>(updatedShippingAddress, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-shipping-address/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        if(shippingAddressService.getShippingAddressById(id).isPresent()) {
            shippingAddressService.deleteShippingAddress(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}