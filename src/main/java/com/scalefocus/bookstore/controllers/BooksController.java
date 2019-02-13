package com.scalefocus.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.entities.BooksList;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.service.IBooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private IBooksService booksService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public BooksList getAllBooks() {
		return booksService.getBooksInBookstore();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	Books addBooks(@RequestBody Books newBook) {
		return booksService.addBooks(newBook);
	}

	@GetMapping(value = "/{book_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	Books getSingleBook(@PathVariable Long bookId) throws Exception {
		return booksService.getBookById(bookId);
	}

	@GetMapping(value = "/authorByBook/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	Authors getAuthorOfTheBook(@PathVariable Long bookId) throws BookStoreServiceException {
		return booksService.getAuthorByBookId(bookId);
	}

	@PutMapping(value = "/setBook/{bookId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Books setBookAuthor(@RequestBody Authors author, @PathVariable Long bookId) throws Exception {
		if (author != null) {
			return booksService.setBookAuthor(bookId, author);
		}
		return null;
	}

//	@GetMapping("/string")
//	public String getString() {
//		return "WORKS!!!";
//	}
}
