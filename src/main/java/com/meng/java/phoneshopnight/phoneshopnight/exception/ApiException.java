package com.meng.java.phoneshopnight.phoneshopnight.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
	
	private final HttpStatus status;
	private final String ErrorMsg;
}
