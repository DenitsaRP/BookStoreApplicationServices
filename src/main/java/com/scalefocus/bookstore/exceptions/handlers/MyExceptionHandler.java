package com.scalefocus.bookstore.exceptions.handlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scalefocus.bookstore.dao.responses.ErrorResponse;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;

@ControllerAdvice(basePackages = { "com.scalefocus.bookstore.controllers" })
public class MyExceptionHandler {

	@ExceptionHandler(BookStoreServiceException.class)
	public final ResponseEntity<ErrorResponse> handleSpecificExceptions(
			BookStoreServiceException bookStoreServiceException) {
		final ErrorResponse errorResponse = new ErrorResponse(new Date(), bookStoreServiceException.getErrorCode(),
				bookStoreServiceException.getMessage(), bookStoreServiceException.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
