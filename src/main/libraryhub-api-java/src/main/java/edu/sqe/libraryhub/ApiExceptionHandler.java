package edu.sqe.libraryhub;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> handle(ApiException e) {
        return ResponseEntity.status(e.getStatus())
                .body(Map.of("error", e.getMessage()));
    }
}