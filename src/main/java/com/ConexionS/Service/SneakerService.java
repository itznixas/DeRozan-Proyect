package com.ConexionS.Service;

import com.ConexionS.Entities.Brand;
import com.ConexionS.Entities.IconicLine;
import com.ConexionS.Entities.Sneakers;
import com.ConexionS.Repository.BrandRepository;
import com.ConexionS.Repository.IconicLineRepository;
import com.ConexionS.Repository.SneakerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class SneakerService {

    @Autowired
    private SneakerRepository sneakerRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private IconicLineRepository iconicLineRepository;

    @Transactional
    public Sneakers createSneaker(Sneakers sneakers) {
        if (sneakers.getBrand() == null || sneakers.getBrand().getId_brand() == null) {
            throw new IllegalArgumentException("Brand information is required");
        }

        Integer brandId = sneakers.getBrand().getId_brand(); // Cambiar según el nombre correcto del campo de ID
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + brandId));
        sneakers.setBrand(brand);

        if (sneakers.getIconicLine() == null || sneakers.getIconicLine().getId_IconicLine() == null) {
            throw new IllegalArgumentException("IconicLine information is required");
        }

        Integer lineId = sneakers.getIconicLine().getId_IconicLine();
        IconicLine iconicLine = iconicLineRepository.findById(lineId)
                .orElseThrow(() -> new EntityNotFoundException("IconicLine not found with id: " + lineId));
        sneakers.setIconicLine(iconicLine);

        return sneakerRepository.save(sneakers);
    }

    public List<Sneakers> getAllSneakers() {
        return sneakerRepository.findAll();
    }

    public Optional<Sneakers> getSneakerById(Integer id) {
        return sneakerRepository.findById(id);
    }

    public Sneakers updateSneaker(Sneakers sneakers) {
        return sneakerRepository.save(sneakers);
    }

    public void deleteSneaker(Integer id) {
        sneakerRepository.deleteById(id);
    }

    public String obtenerRutaImagen(Integer id) {
        Sneakers sneakers = sneakerRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return sneakers.getImage();
    }

    public void deleteImage(Integer id) {
        Sneakers sneakers = sneakerRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado."));
        String imagePath = sneakers.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            File imagefile = new File(imagePath);
            if (imagefile.exists()) {
                imagefile.delete();
            }
        }
        sneakers.setImage(null);
        sneakerRepository.save(sneakers);
    }
}