package com.example.hithron.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidParameters(MethodArgumentNotValidException e)  {
		Map<String, String> errorMapObj = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> errorMapObj.put(error.getField(), error.getDefaultMessage()));
		return errorMapObj;
	}
	
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(UserCustomException.class)
	public Map<String, String> handleUserCustomException(UserCustomException uce) {
		Map<String, String> errorMapObj = new HashMap<>();
		errorMapObj.put("errorMessage", uce.getMessage());
		return errorMapObj;		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleUserNotFoundException(UserNotFoundException uce) {
		Map<String, String> errorMapObj = new HashMap<>();
		errorMapObj.put("errorMessage", uce.getMessage());
		return errorMapObj;		
	}
}
