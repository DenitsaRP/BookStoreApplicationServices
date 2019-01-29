package com.scalefocus.bookstore.enums;

public enum ErrorMessages {

	BOOK_NOT_FOUND(101, "Book Not Found"), //
	AUTHOR_NOT_FOUND(102, "Author Not Found");

	private final int id;
	private final String message;

	ErrorMessages(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return this.id;
	}

	public String getMessege() {
		return this.message;
	}

}
