package com.ConexionS.Service;

import com.ConexionS.Entities.ContactInformation;
import com.ConexionS.Repository.ContactInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInformationService {

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    public Optional<ContactInformation> getContactInformationById(Long id) {
        return contactInformationRepository.findById(id);
    }

    public ContactInformation createContactInformation(ContactInformation contactInformation) {
        return contactInformationRepository.save(contactInformation);
    }

    public ContactInformation updateContactInformation(ContactInformation contactInformation) {
        Long id = contactInformation.getContactId();
        ContactInformation existingContactInformation = contactInformationRepository.findById(id).orElse(null);

        if(existingContactInformation == null) {
            throw new RuntimeException("Contact information with ID" + id + " not found.");
        }

        existingContactInformation.setName(contactInformation.getName());
        existingContactInformation.setLastName(contactInformation.getLastName());
        existingContactInformation.setPrefix(contactInformation.getPrefix());
        existingContactInformation.setPhone(contactInformation.getPhone());

        return contactInformationRepository.save(existingContactInformation);
    }

    public void deleteContactInformationById(Long id) {
        contactInformationRepository.deleteById(id);
    }

}
