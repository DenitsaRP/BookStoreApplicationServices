package com.scalefocus.bookstore.enums;

public enum ErrorMessages {

	BOOK_NOT_FOUND(101, "Book Not Found"), //
	AUTHOR_NOT_FOUND(102, "Author Not Found"), //
	AUTHOR_ALREADY_EXISTS(103, "Author already exists!"), //
	NULL_VALUE(104, "Please, enter correct data!"), //
	BOOK_ALREDY_EXISTS(105, "Author already exists!");

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
