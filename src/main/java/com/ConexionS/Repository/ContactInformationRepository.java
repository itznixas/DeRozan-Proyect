package com.ConexionS.Repository;

import com.ConexionS.Entities.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {

}
