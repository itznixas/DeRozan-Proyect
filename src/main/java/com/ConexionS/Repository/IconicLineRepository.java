package com.ConexionS.Repository;

import com.ConexionS.Entities.IconicLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconicLineRepository extends JpaRepository<IconicLine, Integer> {

}
