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

	@GetMapping(value = "/{authorId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Authors getSingleAuthor(@PathVariable Long authorId) throws BookStoreServiceException {
		return authorService.getAuthorById(authorId);
	}

	@PostMapping(name = "/addAuthor", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Authors addAuthor(@RequestBody Authors newAuthor) throws BookStoreServiceException {
		return authorService.addAuthors(newAuthor);
	}

	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Authors updateAuthor(@RequestBody Authors newAuthor) throws BookStoreServiceException {
		return authorService.updateAuthor(newAuthor);
		
	}

	@DeleteMapping(value = "/delete/{authorId}")
	public void deleteAuthorById(@PathVariable Long authorId) throws BookStoreServiceException {
		authorService.deleteAuthorById(authorId);
	}

}
