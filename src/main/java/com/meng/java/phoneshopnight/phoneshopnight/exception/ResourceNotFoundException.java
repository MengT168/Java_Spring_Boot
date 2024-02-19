package com.meng.java.phoneshopnight.phoneshopnight.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

//	public ResourceNotFoundException(HttpStatus status, String ErrorMsg) {
//		super(status, ErrorMsg);
//		// TODO Auto-generated constructor stub
//	}
	public ResourceNotFoundException(String resourcename , Integer id) {
		super(HttpStatus.NOT_FOUND, "%s with id = %d not found".formatted(resourcename,id));
	}

}
