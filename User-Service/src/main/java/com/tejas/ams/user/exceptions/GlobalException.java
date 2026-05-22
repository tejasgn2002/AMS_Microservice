package com.tejas.ams.user.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tejas.ams.user.util.ResponseMessageUtil;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {
	
	private final ResponseMessageUtil response;
	
	@ExceptionHandler(GeneralError.class)
	public ResponseEntity<Map<String,Object>> generalErrorHandler(GeneralError ex){
		return ResponseEntity.internalServerError().body(response.getErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String,Object>> userNotFoundHandler(UserNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND));
	} 
	
	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<Map<String,Object>> invalidLoginHandler(InvalidLoginException ex){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getErrorMessage(ex.getMessage(), HttpStatus.UNAUTHORIZED));
	}
}
