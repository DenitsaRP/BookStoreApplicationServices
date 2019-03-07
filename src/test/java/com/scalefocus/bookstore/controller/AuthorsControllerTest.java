package com.scalefocus.bookstore.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.scalefocus.bookstore.controllers.AuthorsController;
import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.AuthorsList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.service.IAuthorServices;

public class AuthorsControllerTest {

	private Authors author;
	private AuthorsList authorsList;

	@Mock
	private IAuthorServices authorServices;

	@Before
	public void setUpTest() {
		MockitoAnnotations.initMocks(this);
<<<<<<< HEAD
		author = new Authors(10L, "Author's name", "Description", "Genre");
=======
		author = new Authors(10L, "Author's name", "Description");
>>>>>>> 522c0fb016380232936abaef2767f5c932f0d75e

		authorsList = new AuthorsList(Arrays.asList(author));
	}

	@Test
	public void shouldGetAllAuthorsTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		Mockito.when(authorServices.getAuthorsInBookstore()).thenReturn(authorsList);

		assertEquals(authorsList, authorsController.getAllAuthors());
	}

	@Test
	public void shouldGetAuthorByIdTest() throws BookStoreServiceException {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		Mockito.when(authorServices.getAuthorById(author.getId())).thenReturn(author);

		assertEquals(author, authorsController.getSingleAuthor(10L));
	}

	@Test
	public void shouldAddAuthorTest() throws BookStoreServiceException {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		final Authors newAuthor = new Authors(20L, "NewName", "NewDescription", "NewGenre");
		Mockito.when(authorServices.addAuthors(newAuthor)).thenReturn(newAuthor);

		assertEquals(newAuthor, authorsController.addAuthor(newAuthor));
	}

	@Test
	public void shouldThrowExceptionWhenGetAuthorByWrongIdTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.getSingleAuthor(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenaGetAuthorByNullIdTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.getSingleAuthor(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenAddedAuthorIsNullTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.addAuthor(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenAddedAuthorAlredyExistsTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.addAuthor(author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getMessege());
		}
	}

	@Test
	public void shouldDeleteAuthorByIdTest() throws BookStoreServiceException {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		Mockito.doNothing().when(authorServices).deleteAuthorById(author.getId());

		authorsController.deleteAuthorById(author.getId());

		Mockito.verify(authorServices, Mockito.times(1)).deleteAuthorById(author.getId());

	}

	@Test
	public void shouldThrowExceptionWhennWrongAuthorIdIsEnteredForDeleteTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.deleteAuthorById(250L);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_NOT_FOUND.getMessege());
		}
	}

	@Test
	public void shouldThrowExceptionWhenNullAuthorIdIsEnteredForDeleteTest() {
		final AuthorsController authorsController = new AuthorsController(authorServices);
		try {
			authorsController.deleteAuthorById(null);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}

	}

}
