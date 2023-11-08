package com.project.shopapp.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handleNullPointer(NullPointerException exception) {
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("error message", exception.getMessage());
        return errorMap;
    }

}
