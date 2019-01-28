package com.scalefocus.bookstore.service.implementation;

public class BookNotFoundException extends RuntimeException {

	BookNotFoundException(Long id) {
		super("Could not find book or author with book id:" + id);
	}

}
