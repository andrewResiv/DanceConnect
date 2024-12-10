package com.andrew.danceconnect.DanceConnect.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSensorNotFoundException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "User not found: " + e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    private ResponseEntity<ErrorResponse> handlerException(EventNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Event not found: " + e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(MeasurementNotCreatedException.class)
//    private ResponseEntity<SensorErrorResponse> handlerException(MeasurementNotCreatedException e) {
//        SensorErrorResponse response = new SensorErrorResponse(
//                e.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
