package com.ConexionS.Repository;

import com.ConexionS.Entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Integer> {
}
