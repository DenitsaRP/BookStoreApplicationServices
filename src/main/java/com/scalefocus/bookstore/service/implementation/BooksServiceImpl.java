package com.scalefocus.bookstore.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.BooksRepository;
import com.scalefocus.bookstore.service.IBooksService;

@Service
public class BooksServiceImpl implements IBooksService {

	@Autowired
	BooksRepository booksRepository;

	// ErrorMessages exceptionCode = ErrorMessages.valueOf("BOOK_NOT_FOUND");

	@Override
	public Books getBookById(Long id) throws BookStoreServiceException {
		return booksRepository.findById(id)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND));
	}

	@Override
	public List<Books> getBooksInBookstore() {
		return booksRepository.findAll();
	}

	@Override
	public Books addBooks(Books book) {
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
	public Authors getAuthorByBookId(Long book_id) throws BookStoreServiceException {
		return booksRepository.findById(book_id)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND)).getAuthor();
	}

}
