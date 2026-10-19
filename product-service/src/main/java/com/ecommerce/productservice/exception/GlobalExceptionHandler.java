package com.ecommerce.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errorMessages = new HashMap<>();
        for (FieldError error : errors) {
                errorMessages.put(error.getField() , error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorMessages);
        //badRequest() : définir le statut HTTP 400.
    }


}
