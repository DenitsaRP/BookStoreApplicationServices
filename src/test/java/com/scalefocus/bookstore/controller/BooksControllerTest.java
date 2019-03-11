package com.scalefocus.bookstore.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scalefocus.bookstore.controllers.BooksController;
import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.entities.BooksList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.service.IBooksService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksControllerTest {

	private Books book;
	private Authors author;
	private BooksList booksList;

	@Mock
	private IBooksService booksService;

	@Before
	public void bookSetUp() {
		MockitoAnnotations.initMocks(this);
		author = new Authors(10L, "Author", "AuthorDescription", "Genre");
		book = new Books(1L, 12345L,"name", author, "description");

		booksList = new BooksList(Arrays.asList(book));
	}

	@Test
	public void shouldgetAllBooksInBookstoreTest() {
		Mockito.when(booksService.getAllBooksInBookstore()).thenReturn(booksList);
		final BooksController booksController = new BooksController(booksService);

		assertEquals(booksList, booksController.getAllBooks());
	}

	@Test
	public void shouldGetBookByIdTest() throws BookStoreServiceException {
		final BooksController booksController = new BooksController(booksService);
		Mockito.when(booksService.getBookById(book.getId())).thenReturn(book);

		assertEquals(book, booksController.getSingleBook(book.getId()));
	}

	@Test
	public void shouldGetAuthorOfTheBookTest() throws BookStoreServiceException {
		final BooksController booksController = new BooksController(booksService);
		Mockito.when(booksService.getAuthorByBookId(book.getId())).thenReturn(book.getAuthor());

		assertEquals(book.getAuthor(), booksController.getAuthorOfTheBook(book.getId()));

	}

	@Test
	public void shouldAddNewBookTest() throws BookStoreServiceException {
		final BooksController booksController = new BooksController(booksService);
		final Books newBook = new Books(2L, 54321L, "NewBook", author, "NewDescription");
		Mockito.when(booksService.addBooks(newBook)).thenReturn(newBook);

		assertEquals(newBook, booksController.addBooks(newBook));

	}

	@Test
	public void shoudSetAuthorToTheBookTest() throws BookStoreServiceException {
		final BooksController booksController = new BooksController(booksService);
		Mockito.when(booksService.setBookAuthor(book.getId(), author)).thenReturn(book);

		assertEquals(book, booksController.setBookAuthor(author, book.getId()));
	
	}
	
	@Test
	public void shouldDeleteBookByIdTest() throws BookStoreServiceException {
		final BooksController booksController = new BooksController(booksService);
		Mockito.doNothing().when(booksService).deleteBook(book.getId());

		booksController.deleteBookById(book.getId());

		Mockito.verify(booksService, Mockito.times(1)).deleteBook(book.getId());
	}
	

	@Test
	public void shouldThrowExceptionWhenGetBookByWrongIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.getSingleBook(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shoudthrowExceptionWhenGetBookByNullIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.getSingleBook(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}

	}

	@Test
	public void shoudThrowExceceptionWhenAuthorNotFoundByBookIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.getAuthorOfTheBook(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowNullValueExceptionWhenGetAuthorByNullBookIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.getAuthorOfTheBook(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenNullBookIsAddedTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.addBooks(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenAddedBookAlredyExistsTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.addBooks(book);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_ALREDY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_ALREDY_EXISTS.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenSetNullAuthorToBookTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.setBookAuthor(null, book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenSetAuthorToNullBookIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.setBookAuthor(author, null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenSetAuthorToBookThatAlreadyHaveAuthorTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.setBookAuthor(author, book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenYouSetAuthorToBookWithIncorrectIdTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.setBookAuthor(author, 250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}



	@Test
	public void shouldThrowExceptionWhenBookIdIsEnteredForDeleteTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.deleteBookById(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenNullBookIdIsEnteredForDeleteTest() {
		final BooksController booksController = new BooksController(booksService);
		try {
			booksController.deleteBookById(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

}
