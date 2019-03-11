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
	public Books getBookById(Long bookId) throws BookStoreServiceException {
		if (bookId == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		return booksRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND));
	}

	@Override
	public BooksList getAllBooksInBookstore() {
		return new BooksList(booksRepository.findAll());
	}

	@Override
	public Books addBooks(Books book) throws BookStoreServiceException {
		if (book == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		final Books findByNameBook = booksRepository.findByName(book.getName());
		if (findByNameBook != null) {
			throw new BookStoreServiceException(ErrorMessages.BOOK_ALREDY_EXISTS);
		}
		return booksRepository.save(book);
	}

	@Override
	public Authors getAuthorByBookId(Long bookId) throws BookStoreServiceException {
		if (bookId == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		return booksRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND)).getAuthor();
	}

	@Override
	public Books setBookAuthor(Long bookId, Authors author) throws BookStoreServiceException {
		if (bookId == null || author == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		final Books book = booksRepository.findById(bookId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND));

		if (book.getAuthor() != null) {
			throw new BookStoreServiceException(ErrorMessages.AUTHOR_ALREADY_EXISTS);
		}

		book.setAuthor(author);
		return booksRepository.save(book);
	}
	
	@Override
	public Books updateBook(Books newBook) throws BookStoreServiceException {
		Books oldBook = getBookById(newBook.getId());
		
		oldBook.setName(newBook.getName());
		oldBook.setIsbn(newBook.getIsbn());
		oldBook.setAuthor(newBook.getAuthor());
		oldBook.setDescription(newBook.getDescription());
		
		return booksRepository.save(oldBook);
	}

	@Override
	public void deleteBook(Long bookId) throws BookStoreServiceException {
		if (bookId == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		if (booksRepository.findById(bookId) == null) {
			throw new BookStoreServiceException(ErrorMessages.BOOK_NOT_FOUND);
		}
		booksRepository.deleteById(bookId);
	}



}
