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
import com.scalefocus.bookstore.entities.AuthorsList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.AuthorRepository;
import com.scalefocus.bookstore.service.IAuthorServices;
import com.scalefocus.bookstore.service.implementation.AuthorsService;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorServiceTest {

	private Authors author;
	private AuthorsList authorsList;

	@Mock
	AuthorRepository authorRepository;

	@InjectMocks
	IAuthorServices authorServices = new AuthorsService();

	@Before
	public void setUpTest() {
		MockitoAnnotations.initMocks(this);
		author = new Authors(10L, "Author's name", "Description");
		authorsList = new AuthorsList(Arrays.asList(author));
	}

	@Test
	public void shouldGetAllAuthorsTest() {
		Mockito.when(authorRepository.findAll()).thenReturn(authorsList.getListAuthors());

		final AuthorsList authorsListResult = authorServices.getAuthorsInBookstore();

		Mockito.verify(authorRepository, Mockito.times(1)).findAll();
		assertEquals(authorsListResult.getListAuthors(), authorsList.getListAuthors());
		assertNotNull(authorsListResult.getListAuthors());
		assertEquals(1, authorsListResult.getListAuthors().size());

	}

	@Test
	public void shouldGetAuthorByIdTest() throws BookStoreServiceException {
		final Optional<Authors> optionalAuthor = Optional.ofNullable(author);
		Mockito.when(authorRepository.findById(Mockito.anyLong())).thenReturn(optionalAuthor);

		final Authors authorResult = authorServices.getAuthorById(Mockito.anyLong());

		Mockito.verify(authorRepository, Mockito.times(1)).findById(Mockito.anyLong());
		assertEquals(authorResult, optionalAuthor.get());
		assertNotNull(authorResult);
	}

//	@Test(expected = BookStoreServiceException.class)
//	public void shouldThrowExceptionWhenGetAuthorByNullIdTest() throws BookStoreServiceException {
//		author.setId(null);
//		author = authorServices.getAuthorById(author.getId());
//
//	}

//	@Test(expected = BookStoreServiceException.class)
//	public void shouldThrowExceptionWhenAuthorNotFoundById() throws BookStoreServiceException {
//		Mockito.when(authorRepository.findById(200L)).thenReturn(Optional.ofNullable(null));
//		final Authors authorsTest = authorServices.getAuthorById(200L);
//
//	}

	@Test
	public void shouldAddAuthorTest() throws BookStoreServiceException {
		Mockito.when(authorRepository.save(Mockito.any(Authors.class))).thenReturn(author);
		final Authors authorResult = authorServices.addAuthors(author);
		Mockito.verify(authorRepository, Mockito.times(1)).save(author);
		assertEquals(authorResult, author);
	}

	@Test
	public void shouldThrowExceptionWhenAddExistingAuthorNameTest() throws BookStoreServiceException {
		final String name = author.getName();
		Mockito.when(authorRepository.findByName(name)).thenReturn(author);
		try {
			authorServices.addAuthors(author);
		} catch (final BookStoreServiceException e) {
			Mockito.verify(authorRepository, Mockito.times(1)).findByName(name);
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_ALREADY_EXISTS.getMessege());
			Mockito.verifyNoMoreInteractions(authorRepository);
			Mockito.verify(authorRepository, Mockito.times(0)).save(author);
		}

	}

	@Test
	public void shloudThrowNullValueExpectedExceptionWhenIdIsNullTest() {
		author.setId(null);
		Mockito.when(authorRepository.findById(author.getId())).thenReturn(null);
		try {
			authorServices.getAuthorById(author.getId());
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

	@Test
	public void shouldThrowAuthorNotFoundExceptionWhenIdNotCorrectTest() {
		author.setId(250L);
		Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
		try {
			authorServices.getAuthorById(author.getId());

		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.AUTHOR_NOT_FOUND.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.AUTHOR_NOT_FOUND.getMessege());

		}
	}

	@Test
	public void shouldThrowNullValueExceptionWhenAddedAuthorIsNullTest() {
		author = null;
		try {
			authorServices.addAuthors(author);
		} catch (final BookStoreServiceException e) {
			assertEquals(e.getErrorCode(), ErrorMessages.NULL_VALUE.getId());
			assertEquals(e.getErrorMsg(), ErrorMessages.NULL_VALUE.getMessege());
		}
	}

}
