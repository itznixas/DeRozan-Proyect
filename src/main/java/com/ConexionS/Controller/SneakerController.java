package com.ConexionS.Controller;

import com.ConexionS.Entities.Brand;
import com.ConexionS.Entities.IconicLine;
import com.ConexionS.Entities.Sneakers;
import com.ConexionS.Service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sneakers")
public class SneakerController {

    @Autowired
    private SneakerService sneakerService;

    @PostMapping("/add-sneakers")
    public ResponseEntity<Sneakers> createSneaker(
            @RequestParam("name") String name,
            @RequestParam("id_sneakers") Integer id_sneakers,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("size") Double size,
            @RequestParam("color") String color,
            @RequestParam("category") String category,
            @RequestParam("amount") Integer amount,
            @RequestParam("brand.id_brand") Integer brandId,
            @RequestParam("iconicLine.id_IconicLine") Integer iconicLineId,
            @RequestParam("file") MultipartFile imagen
    ) {
        try {
            Sneakers sneakers = new Sneakers();
            sneakers.setId_sneakers(id_sneakers);
            sneakers.setName(name);
            sneakers.setDescription(description);
            sneakers.setPrice(price);
            sneakers.setSize(size);
            sneakers.setColor(color);
            sneakers.setCategory(category);
            sneakers.setAmount(amount);
            sneakers.setBrand(new Brand(brandId));
            sneakers.setIconicLine(new IconicLine(iconicLineId));

            if (!imagen.isEmpty()) {
                Path directorioImagenes = Paths.get("src/main/resources/static/img");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                try {
                    byte[] bytesImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                    Files.write(rutaCompleta, bytesImg);
                    sneakers.setImage(imagen.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            LocalDateTime localDateTime = LocalDateTime.now();
            Date registrationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            sneakers.setRegistration(registrationDate);
            sneakers.setStatus("activo");

            Sneakers savedSneaker = sneakerService.createSneaker(sneakers);
            return new ResponseEntity<>(savedSneaker, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/get-all-sneakers")
    public ResponseEntity<List<Sneakers>> getAllSneaker() {
        List<Sneakers> sneakers = sneakerService.getAllSneakers();
        return new ResponseEntity<>(sneakers, HttpStatus.OK);
    }

    @GetMapping("/get-sneaker/{id}")
    public ResponseEntity<Sneakers> getSneakerById(@PathVariable Integer id) {
        Optional<Sneakers> sneakers = sneakerService.getSneakerById(id);
        return sneakers.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-sneakers/{id}")
    public ResponseEntity<Sneakers> updateSneaker(@PathVariable Integer id,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("brand.id_brand") Integer brandId,
                                                  @RequestParam("amount") Integer amount,
                                                  @RequestParam("price") Double price,
                                                  @RequestParam("size") Double size,
                                                  @RequestParam("color") String color,
                                                  @RequestParam("iconicLine.id_IconicLine") Integer iconicLineId,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("category") String category,
                                                  @RequestParam("image") MultipartFile image) {
        Optional<Sneakers> sneakersOptional = sneakerService.getSneakerById(id);

        if (sneakersOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sneakers sneakers = sneakersOptional.get();

        if (name == null || name.isEmpty() ||
                brandId == null || iconicLineId == null ||
                amount == null || color == null ||
                description == null || category == null ||
                size == null || price == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sneakers.setName(name);
        sneakers.setBrand(new Brand(brandId));  // Asumiendo que Brand tiene un constructor que acepta solo id
        sneakers.setIconicLine(new IconicLine(iconicLineId));  // Similar para IconicLine
        sneakers.setAmount(amount);
        sneakers.setColor(color);
        sneakers.setDescription(description);
        sneakers.setCategory(category);
        sneakers.setSize(size);
        sneakers.setPrice(price);

        if (!image.isEmpty()) {
            Path directorioImagenes = Paths.get("src/main/resources/static/img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = image.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                sneakers.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        Date registrationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        sneakers.setRegistration(registrationDate);

        Sneakers updatedSneaker = sneakerService.updateSneaker(sneakers);
        return new ResponseEntity<>(updatedSneaker, HttpStatus.OK);
    }

        @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSneaker(@PathVariable Integer id) {
        if (sneakerService.getSneakerById(id).isPresent()) {
            sneakerService.deleteSneaker(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}