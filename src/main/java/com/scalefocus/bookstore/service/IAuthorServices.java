package com.scalefocus.bookstore.service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.AuthorsList;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;

public interface IAuthorServices {

	AuthorsList getAuthorsInBookstore();

	Authors getAuthorById(Long authorId) throws BookStoreServiceException;

	Authors addAuthors(Authors author) throws BookStoreServiceException;

	void deleteAuthorById(Long authorId) throws BookStoreServiceException;
<<<<<<< HEAD
	
	Authors updateAuthor(Authors author) throws BookStoreServiceException;
=======
>>>>>>> 522c0fb016380232936abaef2767f5c932f0d75e

}
