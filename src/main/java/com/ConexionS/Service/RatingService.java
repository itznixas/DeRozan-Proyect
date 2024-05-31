package com.ConexionS.Service;

import com.ConexionS.Entities.Ratings;
import com.ConexionS.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Ratings createRating(Ratings ratings) {
        return ratingRepository.save(ratings);
    }

    public List<Ratings> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Optional<Ratings> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    public Ratings updateRating(Ratings ratings) {
        return ratingRepository.save(ratings);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
