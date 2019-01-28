package com.scalefocus.bookstore.service;

import java.util.List;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;

public interface IBooksService {

	Books setBookAuthor(Long bookId, Authors author);

	Books getBookById(Long id) throws Exception;

	List<Books> getBooksInBookstore();

	Books addBooks(Books book);

	Authors getAuthorByBookId(Long book_id);
}
