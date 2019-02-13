package com.scalefocus.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.entities.BooksList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.BooksRepository;
import com.scalefocus.bookstore.service.IBooksService;

@Service
public class BooksService implements IBooksService {

	@Autowired
	BooksRepository booksRepository;

	@Override
	public Books getBookById(Long id) throws BookStoreServiceException {
		return booksRepository.findById(id)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND));
	}

	@Override
	public BooksList getBooksInBookstore() {
		return new BooksList(booksRepository.findAll());
	}

	@Override
	public Books addBooks(Books book) throws BookStoreServiceException {
		if (book == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		return booksRepository.save(book);
	}

	@Override
	public Books setBookAuthor(Long bookId, Authors author) throws BookStoreServiceException {
		final Books book = booksRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND));

		if (book != null && book.getAuthor() == null) {
			book.setAuthor(author);
			booksRepository.save(book);
			return book;
		}

		return book;
	}

	@Override
	public Authors getAuthorByBookId(Long bookId) throws BookStoreServiceException {
		return booksRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND)).getAuthor();
	}

}
