package com.scalefocus.bookstore.service;

import java.util.List;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;

public interface IAuthorServices {

	List<Authors> getAuthorsInBookstore() throws Exception;

	Authors getAuthorById(Long id) throws BookStoreServiceException;

	Authors addAuthors(Authors author);

}
