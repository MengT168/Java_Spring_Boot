package com.meng.java.phoneshopnight.phoneshopnight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		ErrorRespone errorRespone = new ErrorRespone(e.getStatus(), e.getErrorMsg());
		return ResponseEntity
				.status(e.getStatus())
				.body(errorRespone);
	}
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		ErrorRespone errorRespone = new ErrorRespone(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorRespone);
	}

}
