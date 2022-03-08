package ar.com.metadata.schoolregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumReachedException extends RuntimeException {
    public MaximumReachedException(String exception) {
        super(exception);
    }
}

