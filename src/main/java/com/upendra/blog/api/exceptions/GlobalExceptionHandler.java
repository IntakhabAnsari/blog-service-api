package com.upendra.blog.api.exceptions;

import org.hibernate.ResourceClosedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.upendra.blog.api.dtos.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceClosedException exception){
		String message= exception.getMessage();
		ApiResponse apiResponse= new ApiResponse(message ,true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		
	}

}
