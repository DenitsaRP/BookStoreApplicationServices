package com.scalefocus.bookstore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
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
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.BooksRepository;
import com.scalefocus.bookstore.service.IBooksService;
import com.scalefocus.bookstore.service.implementation.BooksService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BooksServiceTest {

	private Books book;
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
	}

	@Test
	public void setBookAuthorTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(Mockito.anyLong())).thenReturn(optionalBook);
		Mockito.when(booksRepository.save(Mockito.any(Books.class))).thenReturn(optionalBook.get());
		book = booksService.setBookAuthor(1L, author);
		optionalBook.get().setAuthor(author);
		assertEquals(book, optionalBook.get());
		assertNotNull(optionalBook.get().getAuthor());
	}

	@Test(expected = BookStoreServiceException.class)
	public void setBookAuthorNullIdTest() throws BookStoreServiceException {
		book = booksService.setBookAuthor(null, author);
	}

	@Test(expected = BookStoreServiceException.class)
	public void setBookNullAuthorIdTest() throws BookStoreServiceException {
		book = booksService.setBookAuthor(Mockito.anyLong(), null);
	}

	@Test
	@Ignore
	public void setBookAuthorErrorTest() {
		assertTrue(false);
	}

	@Test
	public void getBooksInBookstoreTest() {
		booksList = new BooksList(Arrays.asList(book));
		Mockito.when(booksRepository.findAll()).thenReturn(booksList.getListBooks());
		final BooksList booksListResult = booksService.getBooksInBookstore();
		Mockito.verify(booksRepository, Mockito.times(1)).findAll();
		assertEquals(booksListResult.getListBooks(), booksList.getListBooks());
		assertEquals(1, booksListResult.getListBooks().size());

	}

	@Test
	public void getBookByIdTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(Mockito.anyLong())).thenReturn(optionalBook);
		book = booksService.getBookById(1L);
		assertEquals(book, optionalBook.get());
		assertNotNull(optionalBook.get());
		assertNotNull(book);
	}

	@Test(expected = BookStoreServiceException.class)
	public void getBookByIdNullTest() throws BookStoreServiceException {
		book = booksService.getBookById(null);
	}

	@Test
	public void getAuthorByBookIdTest() throws BookStoreServiceException {
		final Optional<Books> optionalBook = Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(Mockito.anyLong())).thenReturn(optionalBook);
		author = booksService.getAuthorByBookId(1L);
		assertEquals(author, optionalBook.get().getAuthor());
		assertNotNull(optionalBook.get());
	}

	@Test
	public void getNullAuthorByBookIdTest() throws BookStoreServiceException {
		author = booksService.getAuthorByBookId(null);
	}

	@Test
	public void addBookTest() throws BookStoreServiceException {
		Mockito.when(booksRepository.save(Mockito.any(Books.class))).thenReturn(book);
		final Books bookresult = booksService.addBooks(book);
		assertEquals(bookresult, book);
		assertNotNull(bookresult);
	}

	@Test(expected = BookStoreServiceException.class)
	public void addNullBookTest() throws BookStoreServiceException {
		book = booksService.addBooks(null);

	}

}
