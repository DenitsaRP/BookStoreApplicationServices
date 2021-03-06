package com.scalefocus.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	private final IBooksService booksService;

	@Autowired
	public BooksController(IBooksService booksService) {
		this.booksService = booksService;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public BooksList getAllBooks() {
		return booksService.getAllBooksInBookstore();
	}

	@GetMapping(value = "/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Books getSingleBook(@PathVariable Long bookId) throws BookStoreServiceException {
		return booksService.getBookById(bookId);
	}

	@GetMapping(value = "/authorByBook/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Authors getAuthorOfTheBook(@PathVariable Long bookId) throws BookStoreServiceException {
		return booksService.getAuthorByBookId(bookId);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Books addBooks(@RequestBody Books newBook) throws BookStoreServiceException {
		return booksService.addBooks(newBook);
	}

	@PutMapping(value = "/setBook/{bookId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Books setBookAuthor(@RequestBody Authors author, @PathVariable Long bookId)
			throws BookStoreServiceException {
		if (author != null) {
			return booksService.setBookAuthor(bookId, author);
		}
		return null;
	}
	
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE } )
	public Books updateBook(@RequestBody Books newBook) throws BookStoreServiceException {
		return booksService.updateBook(newBook);
	
	}

	@DeleteMapping(value = "/delete/{bookId}")
	public void deleteBookById(@PathVariable Long bookId) throws BookStoreServiceException {
		booksService.deleteBook(bookId);
	}

//	@GetMapping("/string")
//	public String getString() {
//		return "WORKS!!!";
//	}
}
