package com.scalefocus.bookstore.exceptions;

import com.scalefocus.bookstore.enums.ErrorMessages;

public class BookStoreServiceException extends Exception {


	private static final long serialVersionUID = -1307493420921168255L;
	private final int exceptionCode;
	private final String exceptionMsg;

	public BookStoreServiceException(ErrorMessages code) {
		this.exceptionMsg = code.getMessege();
		this.exceptionCode = code.getId();
	}

	public int getErrorCode() {
		return exceptionCode;
	}

	public String getErrorMsg() {
		return exceptionMsg;
	}

}
