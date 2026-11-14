package com.monitoring.device.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Handles exceptions raised by REST controllers across the application.
@RestControllerAdvice
public class GlobalExceptionHandler {

  // Converts duplicate device errors into HTTP 409 Conflict.
  @ExceptionHandler(DuplicateDeviceException.class)
  public ResponseEntity<Map<String, String>> handleDuplicateDevice(
      DuplicateDeviceException exception) {

    Map<String, String> error = new LinkedHashMap<>();
    error.put("error", exception.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  // Converts invalid request fields into HTTP 400 Bad Request.
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationErrors(
      MethodArgumentNotValidException exception) {

    Map<String, String> errors = new LinkedHashMap<>();

    // Collect the validation message for each invalid field.
    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }

  // Converts invalid business input into HTTP 400 Bad Request.
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleInvalidArgument(
      IllegalArgumentException exception) {

    Map<String, String> error = new LinkedHashMap<>();
    error.put("error", exception.getMessage());

    return ResponseEntity.badRequest().body(error);
  }
}
