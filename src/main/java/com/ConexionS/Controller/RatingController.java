package com.ConexionS.Controller;

import com.ConexionS.Entities.Ratings;
import com.ConexionS.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/add-ratings")
    public ResponseEntity<Ratings> createRating(@RequestBody Ratings rating) {
        Ratings saveRating = ratingService.createRating(rating);
        return new ResponseEntity<>(saveRating, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-ratings")
    public ResponseEntity<List<Ratings>> getAllRatings() {
        List<Ratings> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/get-rating/{id}")
    public ResponseEntity<Ratings> getRatingById(@PathVariable int id) {
        Optional<Ratings> rating = ratingService.getRatingById(id);

        return rating.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/update-rating/{id}")
    public ResponseEntity<Ratings> updateRating(@PathVariable int id, @RequestBody Ratings ratingDetails) {
        Optional<Ratings> ratingsOptional = ratingService.getRatingById(id);

        if(ratingsOptional.isPresent()) {
            Ratings ratings = ratingsOptional.get();

            if (ratingDetails != null && ratingDetails.getQualification() != null && ratingDetails.getComments() != null) {

                ratings.setQualification(ratingDetails.getQualification());
                ratings.setComments(ratingDetails.getComments());

                Ratings updateRating = ratingService.updateRating(ratings);
                return new ResponseEntity<>(updateRating, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-rating/{id}")
    public ResponseEntity<Ratings> deleteRating(@PathVariable int id) {
        if(ratingService.getRatingById(id).isPresent()) {
            ratingService.deleteRating(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
