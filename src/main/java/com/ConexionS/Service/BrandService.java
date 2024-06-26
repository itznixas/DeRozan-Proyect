package com.ConexionS.Service;

import com.ConexionS.Entities.Brand;
import com.ConexionS.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public void deleteBrandById(Integer id) {
        brandRepository.deleteById(id);
    }

}
