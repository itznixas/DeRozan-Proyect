package com.ConexionS.Service;

import com.ConexionS.Entities.ShippingAddress;
import com.ConexionS.Repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    public Optional<ShippingAddress> getShippingAddressById(Long id) {
        return shippingAddressRepository.findById(id);
    }

    public Optional<ShippingAddress> getUserShippingAddresses(Long userId) {
        return  shippingAddressRepository.findById(userId);
    }

    public ShippingAddress createShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    public ShippingAddress updateShippingAddress(ShippingAddress shippingAddress) {
        Long id = shippingAddress.getId_sending();
        ShippingAddress existingShippingAddress = shippingAddressRepository.findById(id).orElse(null);

        if(existingShippingAddress == null) {
            throw new RuntimeException("Shipping address with ID" + id + " not found.");
        }

        existingShippingAddress.setCountry(shippingAddress.getCountry());
        existingShippingAddress.setDepartment(shippingAddress.getDepartment());
        existingShippingAddress.setMunicipality(shippingAddress.getMunicipality());
        existingShippingAddress.setAddress(shippingAddress.getAddress());
        existingShippingAddress.setBackupAddress(shippingAddress.getBackupAddress());
        existingShippingAddress.setPostalCode(shippingAddress.getPostalCode());

        return shippingAddressRepository.save(existingShippingAddress);
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressRepository.deleteById(id);
    }
}
