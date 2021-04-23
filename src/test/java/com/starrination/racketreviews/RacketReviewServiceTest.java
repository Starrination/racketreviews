package com.starrination.racketreviews;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class RacketReviewServiceTest {

    @Test
    void calculateAvgRating() {
        RacketReviewService racketReviewService = new RacketReviewService();
        List<RacketReview> racketReviews = new ArrayList<>();
        RacketReview r1 = new RacketReview("Lars", 1, "Bullpadel", 10, "Topp");
        RacketReview r2 = new RacketReview("Erik", 1, "Bullpadel", 5, "Sådär");
        racketReviews.add(r1);
        racketReviews.add(r2);
        Float avg = racketReviewService.calculateAvgRating(racketReviews);
        assertEquals(7.5F, avg);
    }
}