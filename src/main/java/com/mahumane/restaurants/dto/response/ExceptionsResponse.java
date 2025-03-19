package com.mahumane.restaurants.dto.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionsResponse {

    public  static ResponseEntity<Object> exceptionResponse(HttpStatus http, String message){
     return ResponseEntity.status(http).body(
                Map.of(
                        "timestamp",LocalDateTime.now(),
                        "status", http.value(),
                        "error", http.getReasonPhrase(),
                        "message", message
                )
        );
    }

}
