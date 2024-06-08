package com.ConexionS.Controller;

import com.ConexionS.Entities.IconicLine;
import com.ConexionS.Service.IconicLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iconic-lines")
public class IconicLineController {

    @Autowired
    private IconicLineService iconicLineService;

    @PostMapping("/add-iconic-line")
    public ResponseEntity<IconicLine> createIconicLine(@RequestBody IconicLine iconicLine) {
        IconicLine saveIconicLine = iconicLineService.createIconicLine(iconicLine);
        return new ResponseEntity<>(saveIconicLine, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-iconicLines")
    public ResponseEntity<List<IconicLine>> getAllIconicLines() {
        List<IconicLine> iconicLines = iconicLineService.getAllIconicLine();
        return new ResponseEntity<>(iconicLines, HttpStatus.OK);
    }

    @GetMapping("/get-iconic-line/{id}")
    public ResponseEntity<IconicLine> getIconicLineById(@PathVariable Integer id) {
        Optional<IconicLine> iconicLine = iconicLineService.getIconicLineById(id);

        return iconicLine.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-iconic-line/{id}")
    public ResponseEntity<IconicLine> updateIconicLine(@PathVariable Integer id, @RequestBody(required = false) IconicLine iconicLineDetails) {
        Optional<IconicLine> iconicLineOptional = iconicLineService.getIconicLineById(id);

        if(iconicLineOptional.isPresent()) {

            IconicLine iconicLine = iconicLineOptional.get();

            if (iconicLineDetails != null && iconicLineDetails.getName() != null) {

                iconicLine.setName(iconicLineDetails.getName());
                IconicLine updateIconicLine = iconicLineService.updateIconicLine(iconicLine);
                return new ResponseEntity<>(updateIconicLine, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-iconic-line/{id}")
    public ResponseEntity<Void> deleteIconicLine(@PathVariable Integer id) {
        if (iconicLineService.getIconicLineById(id).isPresent()) {
            iconicLineService.deleteIconicLineById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}