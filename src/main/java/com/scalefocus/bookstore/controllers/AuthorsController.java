package com.scalefocus.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.service.IAuthorServices;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

	@Autowired
	private IAuthorServices authorService;

	@GetMapping
	public List<Authors> getAllAuthors() {
		try {
			return authorService.getAuthorsInBookstore();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Authors>();
	}

	@GetMapping(value = "/{author_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Authors getSingleAuthor(@PathVariable Long author_id) throws BookStoreServiceException {
		return authorService.getAuthorById(author_id);
	}

	@PostMapping(name = "/addAuthor", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	Authors addAuthor(@RequestBody Authors newAuthor) {
		return authorService.addAuthors(newAuthor);
	}

}