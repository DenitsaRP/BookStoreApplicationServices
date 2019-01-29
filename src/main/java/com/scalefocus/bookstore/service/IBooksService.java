package com.scalefocus.bookstore.service;

import java.util.List;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;

public interface IBooksService {

	Books setBookAuthor(Long bookId, Authors author) throws BookStoreServiceException;

	Books getBookById(Long id) throws BookStoreServiceException;

	List<Books> getBooksInBookstore();

	Books addBooks(Books book);

	Authors getAuthorByBookId(Long book_id) throws BookStoreServiceException;
}
