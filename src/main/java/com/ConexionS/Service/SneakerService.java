package com.ConexionS.Service;

import com.ConexionS.Entities.Sneakers;
import com.ConexionS.Repository.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class SneakerService {

    @Autowired
    private SneakerRepository sneakerRepository;

    public Sneakers createProduct(Sneakers sneakers) {
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