package com.scalefocus.bookstore.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.repositories.BooksRepository;
import com.scalefocus.bookstore.service.IBooksService;

@Service
public class BooksServiceImpl implements IBooksService {

	@Autowired
	BooksRepository booksRepository;

	@Override
	public Books getBookById(Long id) {
		return booksRepository.findById(id).orElse(null);
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
	public Books setBookAuthor(Long bookId, Authors author) {
		final Books book = booksRepository.findById(bookId).orElseGet(null);

		if (book != null && book.getAuthor() == null) {
			book.setAuthor(author);
			booksRepository.save(book);
			return book;
		}

		return book;
	}

	@Override
	public Authors getAuthorByBookId(Long book_id) {
		return booksRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException(book_id)).getAuthor();
	}

}
