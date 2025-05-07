package com.animalapi.exception;

import com.animalapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleResponseStatusException(ResponseStatusException ex) {
        ApiResponse apiResponse =  new ApiResponse(409, "Conflict", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<ApiResponse> handleAnimalNotFound(AnimalNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(404, "Not Found", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
