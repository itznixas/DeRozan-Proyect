package com.ConexionS.Repository;

import com.ConexionS.Entities.ShoppingBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingBagRepository extends JpaRepository<ShoppingBag, Integer> {
    Optional<ShoppingBag> findByUsers_id(Integer id_user);
}
