package com.starrination.racketreviews;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "resource not found")
public class RacketReviewNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    RacketReviewNotFoundException(Integer id) {
        super("Could not find racket " + id);
    }
}
