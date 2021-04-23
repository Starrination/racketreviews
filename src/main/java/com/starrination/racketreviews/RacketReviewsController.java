package com.starrination.racketreviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class RacketReviewsController {

    @Autowired
    private RacketReviewRepository racketRepository;

    @GetMapping(path="/racketreviews")
    @CrossOrigin()
    public List<RacketReview> getRacketReviews() {
        List<RacketReview> racketReviews = new ArrayList<>();
        for(RacketReview racketReview : racketRepository.findAll()) {
            racketReviews.add(racketReview);
        }
        return racketReviews;
    }

    @GetMapping(path="/racketreviews/{id}")
    @CrossOrigin()
    public RacketReview getRacketReview(@PathVariable Integer id) {
        return racketRepository.findById(id).orElseThrow(() -> new RacketReviewNotFoundException(id));
    }

    @PostMapping(path="/racketreviews")
    @CrossOrigin()
    public RacketReview addRacketReview(@RequestBody RacketReview racketReview) {
        return racketRepository.save(racketReview);
    }

    @PostMapping (path="/racketreviews/{id}")
    @CrossOrigin()
    public RacketReview updateRacketReview(@RequestBody RacketReview newRacketReview, @PathVariable Integer id) {
        return racketRepository.findById(id)
        .map(racketReview -> {
            racketReview.setName(newRacketReview.getName());
            racketReview.setAge(newRacketReview.getAge());
            racketReview.setJersey(newRacketReview.getJersey());
            return racketRepository.save(racketReview);
         })
         .orElseThrow(() -> new RacketReviewNotFoundException(id));
    } 

    @DeleteMapping (path="/racketreviews/{id}")
    @CrossOrigin()
    public void deleteRacketReview(@PathVariable Integer id) {
        racketRepository.deleteById(id);
    } 

}
