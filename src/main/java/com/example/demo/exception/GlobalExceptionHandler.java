package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AdoptionNotFoundException.class)
	public ResponseEntity<String> handleAdoptionNotFoundException(AdoptionNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>("未找到貓咪：" + ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(LovehomeNotFoundException.class)
	public ResponseEntity<String> handleLovehomeNotFoundException(LovehomeNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>("未找到中途之家：" + ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("發生錯誤：" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
