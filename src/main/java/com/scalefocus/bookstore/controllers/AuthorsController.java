package com.scalefocus.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.AuthorsList;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.service.IAuthorServices;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

	private final IAuthorServices authorService;

	@Autowired
	public AuthorsController(IAuthorServices authorServices) {
		this.authorService = authorServices;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public AuthorsList getAllAuthors() {
		return authorService.getAuthorsInBookstore();
	}

	@GetMapping(value = "/{author_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Authors getSingleAuthor(@PathVariable Long author_id) throws BookStoreServiceException {
		return authorService.getAuthorById(author_id);
	}

	@PostMapping(name = "/addAuthor", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Authors addAuthor(@RequestBody Authors newAuthor) throws BookStoreServiceException {
		return authorService.addAuthors(newAuthor);
	}

}
