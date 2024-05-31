package com.ConexionS.Service;

import com.ConexionS.Entities.IconicLine;
import com.ConexionS.Repository.IconicLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IconicLineService {

    @Autowired
    private IconicLineRepository iconicLineRepository;

    public IconicLine createIconicLine(IconicLine iconicLine) {
        return iconicLineRepository.save(iconicLine);
    }

    public List<IconicLine> getAllIconicLine() {
        return iconicLineRepository.findAll();
    }

    public Optional<IconicLine> getIconicLineById(Integer id) {
        return iconicLineRepository.findById(id);
    }

    public IconicLine updateIconicLine(IconicLine iconicLine) {
        return iconicLineRepository.save(iconicLine);
    }

    public void deleteIconicLineById(Integer id) {
        iconicLineRepository.deleteById(id);
    }

}
