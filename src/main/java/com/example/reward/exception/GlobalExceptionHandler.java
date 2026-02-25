package com.example.reward.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException ex) {

		log.error("Exception: {}", ex.getMessage());

		return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
	}
}