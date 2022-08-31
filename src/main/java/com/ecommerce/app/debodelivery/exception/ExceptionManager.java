package com.ecommerce.app.debodelivery.exception;

//import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> dataNotFoundException(DataNotFoundException ex) {
        Map<String, String> info = new HashMap<>();
        info.put("error", "true");
        info.put("message", ex.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String, String>> fileNotFoundException(FileNotFoundException ex) {
        Map<String, String> info = new HashMap<>();
        info.put("error", "true");
        info.put("message", ex.getMessage());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<Map<String, String>> usernameNotFoundException(UsernameNotFoundException ex) {
//        Map<String, String> info = new HashMap<>();
//        info.put("error", "true");
//        info.put("message", ex.getMessage());
//        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
//    }
}
