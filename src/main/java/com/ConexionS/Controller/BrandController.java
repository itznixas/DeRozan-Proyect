package com.ConexionS.Controller;

import com.ConexionS.Entities.Brand;
import com.ConexionS.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/add-brand")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand saveBrand = brandService.createBrand(brand);
        return new ResponseEntity<>(saveBrand, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrand();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/get-brand/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        return brand.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-brand/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Integer id, @RequestBody(required = false) Brand brandDetails) {
        Optional<Brand> brandOptional = brandService.getBrandById(id);

        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            if (brandDetails != null && brandDetails.getName() != null) {
                brand.setName(brandDetails.getName());
                Brand updatedBrand = brandService.updateBrand(brand);
                return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        if(brandService.getBrandById(id).isPresent()) {
            brandService.deleteBrandById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
