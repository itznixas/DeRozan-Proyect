package com.ConexionS.Controller;

import com.ConexionS.Entities.ContactInformation;
import com.ConexionS.Service.ContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/contact-information")
public class ContactInformationController {

    @Autowired
    private ContactInformationService contactInformationService;

    @PostMapping("/add-contact-information")
    public ResponseEntity<ContactInformation> createContactInformation(@RequestBody ContactInformation contactInformation) {
        ContactInformation savedContactInformation = contactInformationService.createContactInformation(contactInformation);
        return new ResponseEntity<>(savedContactInformation, HttpStatus.CREATED);
    }

    @GetMapping("/get-contact-information/{id}")
    public ResponseEntity<ContactInformation> getContactInformationById(@PathVariable Long id) {
        Optional<ContactInformation> contactInformation = contactInformationService.getContactInformationById(id);

        return contactInformation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-contact-information/{id}")
    public ResponseEntity<ContactInformation> updateContactInformation(@PathVariable Long id, @RequestBody(required = false) ContactInformation contactInformationDetails) {
        Optional<ContactInformation> ContactInformationOptional = contactInformationService.getContactInformationById(id);

        if (ContactInformationOptional.isPresent()) {

            ContactInformation contactInformation = ContactInformationOptional.get();

            if (contactInformationDetails != null && contactInformationDetails.getName() != null && contactInformationDetails.getLastName() != null && contactInformationDetails.getPrefix() != null && contactInformationDetails.getPhone() != null) {

                contactInformation.setName(contactInformationDetails.getName());
                contactInformation.setLastName(contactInformationDetails.getLastName());
                contactInformation.setPrefix(contactInformationDetails.getPrefix());
                contactInformation.setPhone(contactInformationDetails.getPhone());

                ContactInformation updatedContactInformation = contactInformationService.updateContactInformation(contactInformation);
                return new ResponseEntity<>(updatedContactInformation, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-contact-information/{id}")
    public ResponseEntity<Void> deleteContactInformationById(@PathVariable Long id) {
        contactInformationService.deleteContactInformationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}