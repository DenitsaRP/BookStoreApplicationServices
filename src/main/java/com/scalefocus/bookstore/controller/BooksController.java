package com.scalefocus.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.scalefocus.bookstore.service.IBooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private IBooksService booksService;

	@GetMapping("")
	public List<Books> getAllBooks() {
		try {
			return booksService.getBooksInBookstore();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Books>();
	}

	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	Books addBooks(@RequestBody Books newBook) {
		return booksService.addBooks(newBook);
	}

	@GetMapping(value = "/{book_id}")
	Books getSingleBook(@PathVariable Long book_id) throws Exception {
		return booksService.getBookById(book_id);
	}

	@GetMapping(value = "/authorByBook/{book_id}")
	Authors getAuthorOfTheBook(@PathVariable Long book_id) {
		return booksService.getAuthorByBookId(book_id);
	}

	@PutMapping(value = "/setAuthor/{bookId}")
	public Books setBookAuthor(@RequestBody Authors author, @PathVariable Long bookId) throws Exception {
		if (author != null) {
			return booksService.setBookAuthor(bookId, author);
		}
		return null;
	}
}
