package com.scalefocus.bookstore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.entities.BooksList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.BooksRepository;
import com.scalefocus.bookstore.service.IBooksService;
import com.scalefocus.bookstore.service.implementation.BooksService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BooksServiceTest {

	private Books book;
	private Books bookSetAuthor;
	private Authors author;
	private BooksList booksList;

	@Mock
	private BooksRepository booksRepository;
	@InjectMocks
	private final IBooksService booksService = new BooksService();

	@Before
	public void bookSetUp() {
		MockitoAnnotations.initMocks(this);
		author = new Authors(10L, "Author", "AuthorDescription");
		book = new Books(1L, "name", author, "description");
		bookSetAuthor = new Books(11L, "nameOfTheBook", null, "Ddescription");

		booksList = new BooksList(Arrays.asList(book));
	}

	@Test
	public void shouldGetAllBooksInBookstoreTest() {
		booksList = new BooksList(Arrays.asList(book));
		Mockito.when(booksRepository.findAll()).thenReturn(booksList.getListBooks());

		final BooksList booksListResult = booksService.getAllBooksInBookstore();

		Mockito.verify(booksRepository, Mockito.times(1)).findAll();
		assertEquals(booksListResult.getListBooks(), booksList.getListBooks());
		assertEquals(1, booksListResult.getListBooks().size());
	}

	@Test
	public void shouldGetBookByIdTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(optionalBook);

		book = booksService.getBookById(1L);

		assertEquals(book, optionalBook.get());
		assertNotNull(optionalBook.get());
		assertNotNull(book);
	}

	@Test
	public void shouldGetAuthorByBookIdTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(optionalBook);

		author = booksService.getAuthorByBookId(1L);

		assertEquals(author, optionalBook.get().getAuthor());
		assertNotNull(optionalBook.get());
	}

	@Test
	public void shouldSetBookAuthorTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(bookSetAuthor);
		Mockito.when(booksRepository.findById(bookSetAuthor.getId())).thenReturn(optionalBook);

		final Books newBook = booksService.setBookAuthor(bookSetAuthor.getId(), author);

		Mockito.verify(booksRepository, Mockito.times(1)).findById(bookSetAuthor.getId());
		Mockito.verify(booksRepository, Mockito.times(1)).save(bookSetAuthor);
		optionalBook.get().setAuthor(author);
		assertNotNull(bookSetAuthor.getAuthor());
		assertNotNull(optionalBook.get().getAuthor());
	}

	@Test
	public void shouldAddBookTest() throws BookStoreServiceException {
		Mockito.when(booksRepository.save(Mockito.any(Books.class))).thenReturn(book);

		final Books bookresult = booksService.addBooks(book);

		assertEquals(bookresult, book);
		assertNotNull(bookresult);
	}

	@Test
	public void shouldThrowExceptionWhenSetAuthorToBookWithBookIdTest() {
		bookSetAuthor.setId(null);
		try {
			booksService.setBookAuthor(bookSetAuthor.getId(), author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenSetNullAuthorToTheBookTest() {
		author = null;
		try {
			booksService.setBookAuthor(bookSetAuthor.getId(), author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenSetBookAuthorToBookWithNotCorrectIdTest() {
		try {
			booksService.setBookAuthor(100L, author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenBookAlreadyHasAuthorTest() {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(optionalBook);

		try {
			booksService.setBookAuthor(book.getId(), author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getMessege());
		}
	}

	@Test
	public void shouldThrowNullvalueExceptionWhenFindBookByNullIdTest() {
		book.setId(null);
		try {
			booksService.getBookById(book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenGetBookWithNotCorrectIdTest() {
		book.setId(250L);
		try {
			booksService.getBookById(book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenNullBookIsAddedTest() {
		book = null;
		try {
			booksService.addBooks(book);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenAddedBookAlredyExistsTest() {
		try {
			booksService.addBooks(book);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_ALREDY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_ALREDY_EXISTS.getMessege());
		}
	}

	@Test
	public void shoutdThrowExceptionWhenGetAuthorByNullBookIdTest() {
		book.setId(null);
		try {
			booksService.getAuthorByBookId(book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenGetAuthorWithNotCorrectBookIdTest() {
		book.setId(250L);
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(book.getId())).thenReturn(optionalBook);
		try {
			booksService.getAuthorByBookId(book.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldDeleteBookByIdTest() throws BookStoreServiceException {
		Mockito.doNothing().when(booksRepository).deleteById(book.getId());

		booksService.deleteBook(book.getId());

		Mockito.verify(booksRepository, Mockito.times(1)).deleteById(book.getId());

	}

	@Test
	public void shouldThrowExceptionWhenDeleteBookWithNullIdTest() {
		try {
			booksService.deleteBook(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenDeleteBookWithNotCorrectIdTest() {
		try {
			booksService.deleteBook(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.BOOK_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.BOOK_NOT_FOUND.getMessege());
		}
	}
}
