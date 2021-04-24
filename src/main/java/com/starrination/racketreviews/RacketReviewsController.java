package com.starrination.racketreviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RacketReviewsController {

    @Autowired
    private RacketReviewService racketReviewService;

    @Autowired
    private RacketReviewRepository racketRepository;

    @GetMapping(path="/racketreviews")
    @CrossOrigin()
    public List<RacketReview> getAllRacketReviews() {
        return racketReviewService.getAllRacketReviews();
    }

    @GetMapping(path="/racketreviews/{id}")
    @CrossOrigin()
    public RacketReview getRacketReview(@PathVariable Integer id) {
        return racketReviewService.getRacketReview(id);
    }

    @PostMapping(path="/racketreviews")
    @CrossOrigin()
    public  ResponseEntity<RacketReview> addRacketReview(@RequestBody RacketReview racketReview) {
        racketRepository.save(racketReview);
        return new ResponseEntity<>(racketReview, HttpStatus.CREATED);
    }

    @PostMapping (path="/racketreviews/{id}")
    @CrossOrigin()
    public RacketReview updateRacketReview(@RequestBody RacketReview newRacketReview, @PathVariable Integer id) {
        return racketRepository.findById(id)
        .map(racketReview -> {
            racketReview.setReviewerName(newRacketReview.getReviewerName());
            racketReview.setRating(newRacketReview.getRating());
            racketReview.setReviewText(newRacketReview.getReviewText());
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
