package com.ConexionS.Repository;

import com.ConexionS.Entities.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerRepository extends JpaRepository<Sneakers, Integer> {
}
