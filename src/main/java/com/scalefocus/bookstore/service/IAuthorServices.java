package com.scalefocus.bookstore.service;

import java.util.List;

import com.scalefocus.bookstore.entities.Authors;

public interface IAuthorServices {

	List<Authors> getAuthorsInBookstore() throws Exception;

	Authors getAuthorById(Long id);

	Authors addAuthors(Authors author);

}
