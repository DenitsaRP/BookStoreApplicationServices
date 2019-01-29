package com.scalefocus.bookstore.dao.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private Date timestamp;
	private int code;
	private String message;
	private String details;

}
