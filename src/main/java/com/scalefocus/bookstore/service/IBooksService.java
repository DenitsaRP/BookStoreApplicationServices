package com.scalefocus.bookstore.service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.entities.BooksList;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;

public interface IBooksService {

	Books setBookAuthor(Long bookId, Authors author) throws BookStoreServiceException;

	Books getBookById(Long id) throws BookStoreServiceException;

	BooksList getBooksInBookstore();

	Books addBooks(Books book);

	Authors getAuthorByBookId(Long bookId) throws BookStoreServiceException;
}
