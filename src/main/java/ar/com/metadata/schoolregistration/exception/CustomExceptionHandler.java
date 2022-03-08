package ar.com.metadata.schoolregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customException(CustomException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("BAD REQUEST: " + ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("RECORD NOT FOUND: " + ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaximumReachedException.class)
    public ResponseEntity<Object> handleMaximumReachedException(MaximumReachedException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("MAXIMUM REACHED: " + ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}