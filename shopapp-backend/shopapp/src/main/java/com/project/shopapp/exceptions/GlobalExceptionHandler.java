package com.project.shopapp.exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        return ResponseEntity.status(500).body("Internal Server Error");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        HashMap<String, String> errorResponse = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errorResponse.put("message", fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }
}

