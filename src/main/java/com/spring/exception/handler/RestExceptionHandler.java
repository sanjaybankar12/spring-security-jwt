package com.spring.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.exception.ErrorMessage;
import com.spring.exception.ProductNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handlerProductNotFoundException(ProductNotFoundException ex) {
		return new ResponseEntity<>(new ErrorMessage(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex) {
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleAccessDeniedException(UsernameNotFoundException ex) {
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
	}
}
