package com.starrination.racketreviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RacketReviewService {

    @Autowired
    private RacketReviewRepository racketRepository;

    public List<RacketReview> getAllRacketReviews() {
        List<RacketReview> allReviews = new ArrayList<>();
        for(RacketReview racketReview : racketRepository.findAll()) {
            allReviews.add(racketReview);
        }
        Map<Integer, List<RacketReview>> racketReviewMap = new HashMap<>();
        for (RacketReview review : allReviews) {
            racketReviewMap.putIfAbsent(review.getRacketId(), new ArrayList<>());
            racketReviewMap.get(review.getRacketId()).add(review);
        }
        for (RacketReview review : allReviews) {
            Float avgRating = calculateAvgRating(racketReviewMap.get(review.getRacketId()));
            review.setRacketAvgRating(avgRating);
        }
        return allReviews;
    }

    public RacketReview getRacketReview (Integer id) {
        RacketReview review = racketRepository.findById(id).orElseThrow(() -> new RacketReviewNotFoundException(id));
        Float avgRating = avgRacketRating (review.getRacketId());
        review.setRacketAvgRating(avgRating);
        return review;
    }

    public Float avgRacketRating(Integer racketId) {
        List<RacketReview> allReviews = (List<RacketReview>) racketRepository.findAll();
        List<RacketReview> reviewsForRacket = allReviews.stream().filter(p -> p.getRacketId().equals(racketId)).collect(Collectors.toList());
        return calculateAvgRating(reviewsForRacket);
    }

    Float calculateAvgRating (List<RacketReview> reviewsForRacket) {
        if(reviewsForRacket != null && !reviewsForRacket.isEmpty()) {
            int numberOfReviews = reviewsForRacket.size();
            List<Integer> ratings = reviewsForRacket.stream().map(RacketReview::getRating).collect(Collectors.toList());
            int sum = ratings.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            return (float) sum /numberOfReviews;
        }
        return null;
    }

}
