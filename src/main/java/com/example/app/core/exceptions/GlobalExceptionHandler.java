package com.example.app.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.ErrorDataResult;
import com.example.app.core.utilities.results.ErrorResult;
import com.example.app.core.utilities.results.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DataResult<Object>> handleMethodException(MethodArgumentNotValidException ex, WebRequest webRequest)
	{
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError : ex.getBindingResult().getFieldErrors())
		{
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return new ResponseEntity<DataResult<Object>>(new ErrorDataResult<Object>(validationErrors), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result> handleRuntimeException(Exception ex) {
		if(ex instanceof BadCredentialsException)
			return new ResponseEntity<>(new ErrorResult("Username or password is invalid"), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(new ErrorResult(ex.getMessage()), HttpStatus.FORBIDDEN);
	}

}
